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

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * An item decoration that applies startPadding to the edges only.
 *
 * If the orientation passed is [RecyclerView.HORIZONTAL], left startPadding is set at position 0
 * and right endPadding is set at the last position.
 *
 * If the orientation passed is [RecyclerView.VERTICAL], top startPadding is set at position 0
 * and bottom endPadding is set at the last position.
 */
class HorizontalInnerSpaceDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutManager: RecyclerView.LayoutManager = parent.layoutManager!!
        val layoutParams = view.layoutParams as RecyclerView.LayoutParams
        val position = layoutParams.bindingAdapterPosition
        val itemCount = layoutManager.itemCount

        if (position == 0) {
            outRect.left = 0
        }
        if (position == itemCount - 1) {
            outRect.right = 0
        }

        outRect.right = space

//        if (position != itemCount - 1) {
//            outRect.right = space
//        }
    }
}
