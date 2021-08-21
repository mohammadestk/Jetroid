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

package commons.ui.extention

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Observe a flow on start event and clear it on stop event
 *
 * @author Mohammad Esteki
 */
data class FragmentObserver<T>(
    val fragment: Fragment,
    val flow: Flow<T>,
    val collector: suspend (T) -> Unit
) {
    init {
        fragment.viewLifecycleOwnerLiveData.observe(
            fragment,
            { viewLifeCycleOwner ->
                FlowObserver(viewLifeCycleOwner, flow, collector)
            }
        )
    }
}

/**
 * Observe flow on fragment
 *
 * @param fragment Target fragment for observe flow
 */
fun <T> Flow<T>.observeIn(
    fragment: Fragment
) = FragmentObserver(fragment, this) {}

/**
 * Observe a flow on start event and clear it on stop event
 *
 * @author Mohammad Esteki
 */
class FlowObserver<T>(
    lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit
) {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(
            LifecycleEventObserver { source: LifecycleOwner,
                event: Lifecycle.Event ->
                when (event) {
                    Lifecycle.Event.ON_START ->
                        job = source.lifecycleScope.launch {
                            flow.collect { collector(it) }
                        }
                    Lifecycle.Event.ON_STOP -> {
                        job?.cancel()
                        job = null
                    }
                    else -> Timber.d(event.toString())
                }
            }
        )
    }
}
