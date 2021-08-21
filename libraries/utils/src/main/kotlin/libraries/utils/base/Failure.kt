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
 * Failures occurred on running operations in app.
 *
 * @author Mohammad Esteki
 */
sealed class Failure {

    /**
     * Server operations failure
     */
    object ServerFailure : Failure()

    /**
     * Database operations failure
     */
    object CacheFailure : Failure()

    /**
     * Not found resource failure
     */
    object NotFoundFailure : Failure()

    /**
     * Conflict failure
     */
    object ConflictFailure : Failure()

    /**
     * Mapping objects to each other failure
     */
    object ModelingFailure : Failure()

    /**
     * Unauthorized action failure
     */
    object UnauthorizedFailure : Failure()

    /**
     * Forbidden action failure
     */
    data class ForbiddenFailure(val message: String) : Failure()

    /**
     * Invalid input data failure
     */
    data class InvalidInputFailure(val message: String) : Failure()

    /**
     * Bad failure
     */
    object BadFailure : Failure()

    /**
     * Other server failure
     */
    object OtherServerFailure : Failure()

    /**
     * Connection failure
     */
    object ConnectionFailure: Failure()

    /**
     * Undefined failure
     */
    object UnknownFailure : Failure()
}
