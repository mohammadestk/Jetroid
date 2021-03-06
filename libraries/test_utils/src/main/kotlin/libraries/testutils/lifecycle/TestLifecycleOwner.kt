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

package libraries.testutils.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class TestLifecycleOwner : LifecycleOwner, Lifecycle() {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun getCurrentState(): State = lifecycleRegistry.currentState

    override fun addObserver(observer: LifecycleObserver) {
        lifecycleRegistry.addObserver(observer)
    }

    override fun removeObserver(observer: LifecycleObserver) {
        lifecycleRegistry.removeObserver(observer)
    }
}
