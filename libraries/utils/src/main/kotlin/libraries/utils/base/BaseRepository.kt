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

import arrow.core.Either
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException

const val API_BAD_REQUEST = 400
const val API_NOT_FOUND = 404
const val API_FORBIDDEN = 403
const val API_UNAUTHORIZED = 401
const val API_CONFLICT = 409
const val API_UNPROCESSABLE_ENTITY = 422
const val INTERNAL_SERVER_ERROR = 500

/**
 * Base interface to describe different repository
 *
 * @author Mohammad Esteki
 */
interface BaseRepository {

    /**
     * Run block parameter in try catch block and return executed output. in error cases map them
     * to domain failure.
     *
     * @param block Method should be execute in try catch block
     * @return Executed block output
     */
    suspend fun <T> tryCatch(
        block: suspend () -> T,
    ): Either<Failure, T> =
        try {
            Either.Right(block())
        } catch (e: Exception) {
            Either.Left(mapException(e))
        }

    /**
     * Map domain exceptions to failures.
     *
     * @param e Domain exception
     * @return Domain failures
     */
    fun mapException(e: Exception): Failure {
        e.printStackTrace()
        return when (e) {
            is HttpException ->
                when (e.code()) {
                    API_BAD_REQUEST -> Failure.BadFailure
                    API_UNAUTHORIZED -> Failure.UnauthorizedFailure
                    API_FORBIDDEN -> Failure.ForbiddenFailure(e.message())
                    API_NOT_FOUND -> Failure.NotFoundFailure
                    API_CONFLICT -> Failure.ConflictFailure
                    API_UNPROCESSABLE_ENTITY -> handle422(e)
                    INTERNAL_SERVER_ERROR -> Failure.ServerFailure
                    else -> Failure.OtherServerFailure
                }
            is ServerException -> Failure.ServerFailure
            is CacheException -> Failure.CacheFailure
            is NotFoundException -> Failure.NotFoundFailure
            is ConflictException -> Failure.ConflictFailure
            is UnprocceableEntityException -> Failure.InvalidInputFailure("invalid input message")
            is InternalServerException -> Failure.OtherServerFailure
            is ForbiddenException -> Failure.ForbiddenFailure(e.message ?: "unknown")
            is UnknownHostException -> Failure.ConnectionFailure
            is JsonSyntaxException -> Failure.ModelingFailure
            else -> Failure.UnknownFailure
        }
    }

    private fun handle422(httpException: HttpException): Failure.InvalidInputFailure {
        val response = httpException.response()?.errorBody()?.string()
        return response?.let {
            try {
                val gson = Gson()
                val baseResponse = gson.fromJson(response, BaseResponse::class.java)
                val myType = object : TypeToken<List<ValidationResponse>>() {}.type
                val detailJson = gson.toJsonTree(baseResponse.detail).asJsonArray
                val detail = gson.fromJson<List<ValidationResponse>>(
                    detailJson,
                    myType
                )
                val errorMessage = detail.joinToString(separator = "\n") {
                    "${it.loc.last()}: ${it.msg}"
                }
                Failure.InvalidInputFailure(errorMessage)
            } catch (e: Exception) {
                Failure.InvalidInputFailure(e.cause?.localizedMessage.toString())
            }
        } ?: run {
            Failure.InvalidInputFailure(httpException.message())
        }
    }
}
