/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package commons.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber

/**
 * BaseViewModel responsible for preparing and managing the data for [ViewModel].
 *
 * @author Mohammad Esteki
 * @see ViewModel
 */
abstract class BaseViewModel<VE : Effect, VI : Intent, VS : State> : ViewModel() {

    private val viewIntent = Channel<VI>(Channel.UNLIMITED)

    private val _viewState: MutableStateFlow<VS> by lazy { MutableStateFlow(initialState) }
    val viewState: StateFlow<VS> by lazy { _viewState.asStateFlow() }
    protected abstract val initialState: VS
    protected val currentState: VS get() = _viewState.value

    private val _viewEffect = Channel<VE>(Channel.BUFFERED)
    val viewEffect = _viewEffect.receiveAsFlow()

    /**
     * Called to process intent which user fired.
     *
     * @param intent fired intent from user or app itself
     * @see BaseViewIntent
     */
    protected abstract suspend fun processViewIntent(intent: VI)

    /**
     * When view model created observe user intents on view model scope with IO dispatcher. IO
     * dispatcher allows to run IO operation such as database actions.
     */
    init {
        viewIntent.receiveAsFlow()
            .onEach { processViewIntent(it) }
            .launchIn(viewModelScope.plus(Dispatchers.IO))
            .runCatching { Timber.d("TAG: ERROR") }
    }

    /**
     * Shortcut way to launch coroutines for IO actions.
     *
     * @param block The method that want to be launched on IO dispatcher.
     */
    protected fun launchIo(block: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) { block() }

    /**
     * Send new intent
     */
    fun sendIntent(intent: VI) = launchIo { viewIntent.send(intent) }

    /**
     * Set new Ui State
     */
    protected suspend fun setState(reduce: VS.() -> VS) {
        val newState = _viewState.value.reduce()
        _viewState.emit(newState)
    }

    /**
     * Set new Effect
     */
    protected suspend fun setEffect(builder: () -> VE) {
        val effectValue = builder()
        _viewEffect.send(effectValue)
    }
}
