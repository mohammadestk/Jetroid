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

import android.widget.ListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import commons.ui.R

/**
 * Get implementation manger that lays out items in a grid.
 *
 * @return Recycle view grid layout manager if configured, otherwise null.
 */
val RecyclerView.gridLayoutManager: GridLayoutManager?
    get() = layoutManager as? GridLayoutManager

/**
 * Get implementation manager which provides similar functionality [ListView].
 *
 * @return Recycle view linear layout manager if configured, otherwise null.
 */
val RecyclerView.linearLayoutManager: LinearLayoutManager?
    get() = layoutManager as? LinearLayoutManager

/**
 * Add Space decoration to start and end of recycler view
 */
fun RecyclerView.addSpaceDecoration() {
    addItemDecoration(
        LinearEdgeDecoration(
            startPadding = resources.getDimensionPixelOffset(
                R.dimen.extra_padding
            ),
            endPadding = resources.getDimensionPixelOffset(
                R.dimen.extra_padding
            ),
            orientation = RecyclerView.HORIZONTAL
        )
    )
}
