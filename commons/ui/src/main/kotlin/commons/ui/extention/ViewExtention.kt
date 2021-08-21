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

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import java.util.Locale

/**
 * Simplification to setup view as enable.
 */
fun View.enable() {
    isEnabled = true
}

/**
 * Simplification to setup view as disable.
 */
fun View.disable() {
    isEnabled = false
}

/**
 * Simplification to setup view as visible.
 */
fun View.visible() {
    visibility = VISIBLE
}

/**
 * Simplification to setup view as invisible.
 */
fun View.invisible() {
    visibility = INVISIBLE
}

/**
 * Simplification to setup view as gone.
 */
fun View.gone() {
    visibility = GONE
}

/**
 * Show count with animation 0 to count
 *
 * @param count object count in integer
 */
fun TextView.animateCount(
    count: Int
) {
    val animator = ValueAnimator.ofInt(0, count)
    animator.duration = 2000

    animator.addUpdateListener { animation ->
        text = animation.animatedValue.toString()
    }
    animator.start()
}

/**
 * Show time in 00:00 format with animation
 *
 * @param seconds time in seconds
 */
fun TextView.animateTime(
    seconds: Long
) {
    val animator = ValueAnimator.ofInt(0, seconds.toInt())
    animator.duration = 2000

    animator.addUpdateListener { animation ->
        try {
            val value = animation.animatedValue.toString().toLong()

            val hours = value / 60 / 60
            val totalMinutes = value / 60
            val hourMinutes = hours * 60
            val minutes = totalMinutes - hourMinutes
            val result = String.format(Locale.getDefault(), "%01d:%02d", hours, minutes)

            text = result
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    animator.start()
}

/**
 * Show time in hour format with animation
 *
 * @param seconds time in seconds
 */
@SuppressLint("SetTextI18n")
fun TextView.animateHour(
    seconds: Long
) {
    val animator = ValueAnimator.ofInt(0, seconds.toInt())
    animator.duration = 2000

    if (!text.isNullOrBlank() && text.toString().toLong() == seconds / 60 / 60) {
        return
    }

    animator.addUpdateListener { animation ->
        val value = animation.animatedValue.toString().toLong()

        val hours = value / 60 / 60

        text = hours.toString()
    }
    animator.start()
}
