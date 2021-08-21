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
 * Server exception.
 *
 * @author Mohammad Esteki
 */
class ServerException : Exception()

/**
 * Internal server exception (500).
 *
 * @author Mohammad Esteki
 */
class InternalServerException : Exception()

/**
 * Unprocessable entity exception (422).
 *
 * @author Mohammad Esteki
 */
class UnprocceableEntityException : Exception()

/**
 * Forbidden exception (403).
 *
 * @author Mohammad Esteki
 */
class ForbiddenException(error: String?) : Exception(error)

/**
 * Unauthorized exception (401).
 *
 * @author Mohammad Esteki
 */
class UnauthorizedException : Exception()

/**
 * Internal server exception (404).
 *
 * @author Mohammad Esteki
 */
class NotFoundException : Exception()

/**
 * Conflict exception (409).
 *
 * @author Mohammad Esteki
 */
class ConflictException : Exception()

/**
 * Cache exception. will occurred on local persistence.
 *
 * @author Mohammad Esteki
 */
class CacheException(message: String) : Exception(message)
