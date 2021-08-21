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

/**
 * Base effect interface to describe different effect that like fire-and-forget.
 * In Android, we have certain actions that are more like fire-and-forget, for example- Toast,
 * in those cases, we can not use ViewState as it maintains state.
 * It means, if we use ViewState to show a Toast, it will be shown again on configuration change
 * or every time there is a new state unless and until we reset its state by passing
 * ‘toast is shown’ event. And if you do not wish to do that, you can use ViewEffect
 * as it is based on SingleLiveEvent and does not maintain state.
 *
 * @author Mohammad Esteki
 */
interface BaseViewEffect
