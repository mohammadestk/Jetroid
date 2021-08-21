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

import com.google.gson.annotations.SerializedName

/**
 * Base response of all API Endpoint.
 *
 * @param success The status of request is success or failed.
 * @param message The message of processing request on server.
 * @param data The generic data such as list, object etc.
 */
@OpenForTesting
data class BaseResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("detail")
    val detail: Any?,
    @SerializedName("data")
    val data: T?
)

