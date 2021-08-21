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

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import commons.ui.R
import java.io.File
import kotlin.random.Random

private const val IMAGE_CORNER_RADIUS = 8f


/**
 * Load image with rounded transformation
 *
 * @param url Image network or local url
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadImage(url: String?, @DrawableRes placeholderId: Int? = null) = url?.let {
    load("http://194.5.193.96:8041/$url") {
        imageRequestBuilder(
            this,
            placeholderId,
            this@loadImage
        )
    }
} ?: run {
    load(R.drawable.ic_error_image) {
        imageRequestBuilder(
            this,
            placeholderId,
            this@loadImage
        )
    }
}


/**
 * Load image with rounded transformation
 *
 * @param url Image network or local url
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadRoundedImage(url: String, @DrawableRes placeholderId: Int? = null) =
    load(url) {
        imageRequestBuilderRounded(
            this,
            placeholderId,
            this@loadRoundedImage
        )
    }

/**
 * Load image with rounded transformation
 *
 * @param file Local file
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadRoundedImage(file: File, @DrawableRes placeholderId: Int? = null) =
    load(file) {
        imageRequestBuilderRounded(
            this,
            placeholderId,
            this@loadRoundedImage
        )
    }

/**
 * Load image with rounded transformation
 *
 * @param uri Local uri address
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadRoundedImage(uri: Uri, @DrawableRes placeholderId: Int? = null) =
    load(uri) {
        imageRequestBuilderRounded(
            this,
            placeholderId,
            this@loadRoundedImage
        )
    }

/**
 * Load image with circle transformation
 *
 * @param uri local uri address
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadImageCircle(uri: Uri, @DrawableRes placeholderId: Int? = null) =
    load(uri) {
        imageRequestBuilderCircle(this, placeholderId, this@loadImageCircle)
    }

/**
 * Load image with circle transformation
 *
 * @param url image network or local url
 * @param placeholderId Placeholder res id when loading image
 */
fun ImageView.loadImageCircle(url: String, @DrawableRes placeholderId: Int? = null) =
    load(url) {
        imageRequestBuilderCircle(this, placeholderId, this@loadImageCircle)
    }

/**
 * Build image load request
 *
 * @param builder image request builder
 * @param placeholderId Placeholder res id when loading image
 * @param imageView target image view
 */
private fun imageRequestBuilder(
    builder: ImageRequest.Builder,
    placeholderId: Int?,
    imageView: ImageView
) =
    builder.crossfade(true)
        .scale(Scale.FIT)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .placeholder(
            placeholderId?.let {
                ContextCompat.getDrawable(imageView.context, it)
            } ?: builder.run {
                val placeholdersColors = imageView.resources.getStringArray(R.array.placeholders)
                val placeholderColor = placeholdersColors[Random.nextInt(placeholdersColors.size)]
                ColorDrawable(Color.parseColor(placeholderColor))
            }
        )
        .error(R.drawable.ic_error_image)

/**
 * Build rounded image load request
 *
 * @param builder Image request builder
 * @param placeholderId Placeholder res id when loading image
 * @param imageView Target image view
 */
private fun imageRequestBuilderRounded(
    builder: ImageRequest.Builder,
    placeholderId: Int?,
    imageView: ImageView
) {
    imageRequestBuilder(
        builder,
        placeholderId,
        imageView
    ).transformations(
        RoundedCornersTransformation(IMAGE_CORNER_RADIUS)
    ).build()
}

/**
 * Build circle image load request
 *
 * @param builder Image request builder
 * @param placeholderId Placeholder res id when loading image
 * @param imageView Target image view
 */
private fun imageRequestBuilderCircle(
    builder: ImageRequest.Builder,
    placeholderId: Int?,
    imageView: ImageView
) {
    imageRequestBuilder(
        builder,
        placeholderId,
        imageView
    ).transformations(
        CircleCropTransformation()
    ).build()
}
