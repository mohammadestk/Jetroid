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

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Get resource string from optional id
 *
 * @param resId Resource string identifier.
 * @return The key value if exist, otherwise empty.
 */
fun Context.getString(@StringRes resId: Int?) =
    resId?.let {
        getString(it)
    } ?: run {
        ""
    }

/**
 * Get compat color resource of simple color resource
 *
 * @param resId Color resource
 * @return Compat color resource
 */
fun Context.getColorCompat(@ColorRes resId: Int): Int =
    ContextCompat.getColor(this, resId)
