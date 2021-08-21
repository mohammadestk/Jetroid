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

package libraries.utils.base

/**
 * Base interface to describe different use case
 *
 * @author Mohammad Esteki
 */
interface BaseUseCase<in P, out R> {
    /**
     * Execute use case
     *
     * @param param input parameter of use case, can be null
     *
     * @return Instance of use case execution result
     */
    suspend operator fun invoke(param: P): R
}

/**
 * Class for use cases without parameter
 *
 * @author Mohammad Esteki
 */
open class NoParam
