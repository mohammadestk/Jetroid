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
 * List response for pagination requests
 *
 * @author Mohammad Esteki
 * @param data Requested model list
 * @param limit Size of paginate list
 * @param offset Start point of pagination
 * @param total Total number of entities
 * @param previous Previous page link
 * @param next Next page link
 */
data class PagedList<T>(
    @SerializedName("result")
    val data: List<T>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("next")
    val next: String?
)
