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

import java.io.File
import java.io.IOException
import kotlin.random.Random
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink

private const val BUFFER_SIZE = 2048
private const val PERCENT = 100
private const val OFFSET = 0
private const val EOF = -1

/**
 * Convert file to [MultipartBody.Part].
 *
 * @param partName Name of part
 */
fun File.toPart(partName: String): MultipartBody.Part {
    // create RequestBody instance from file
    val requestFile = asRequestBody("multipart/form-data".toMediaTypeOrNull())

    // MultipartBody.Part is used to send also the actual file name
    return MultipartBody.Part.createFormData(partName, name, requestFile)
}

/**
 * Convert list of files to list of [MultipartBody.Part].
 *
 * @param partName Name of part
 */
fun List<File>?.toPartList(partName: String): List<MultipartBody.Part>? {
    this?.let {
        // create RequestBody instance from file
        val partList: MutableList<MultipartBody.Part> = ArrayList()
        this.forEach {
            // MultipartBody.Part is used to send also the actual file name
            val requestFile = it.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            partList.add(
                MultipartBody.Part.createFormData(partName + Random.nextInt(), it.name, requestFile)
            )
        }
        return partList
    }

    return null
}

/**
 * Convert file to [MultipartBody.Part]. also return upload progress to callback function.
 *
 * @param partName Name of part
 * @param progress Should be update with upload progress
 */
suspend fun File.toPart(partName: String, progress: suspend (Int) -> Unit) =
    MultipartBody.Part.createFormData(
        partName,
        name,
        object : RequestBody() {

            override fun contentType(): MediaType? {
                return "image/*".toMediaTypeOrNull()
            }

            override fun contentLength(): Long = length()

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                val buffer = ByteArray(BUFFER_SIZE)
                var uploaded: Long = 0
                inputStream().use { fileInputStream ->
                    var read: Int
                    while (fileInputStream.read(buffer).also { read = it } != EOF) {
                        uploaded += read.toLong()
                        sink.write(buffer, OFFSET, read)
                        val p = uploaded * PERCENT / length()
                        runBlocking { progress(p.toInt()) }
                    }
                }
            }
        }
    )
