package com.stephenleedev.neighborandroid.util.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.util.extension.toDp

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

class GridListDecoration(
    private val spanCount: Int,
    private val paddingStartEnd: Int = 0,
    private val padding: Int = 10,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position % spanCount == 0) {
            outRect.apply {
                left = paddingStartEnd.toDp
                right = padding.toDp
                top = padding.toDp
            }
        }
        else if (position % spanCount == spanCount - 1) {
            outRect.apply {
                right = paddingStartEnd.toDp
                top = padding.toDp
            }
        }
        else outRect.apply {
            right = padding.toDp
            top = padding.toDp
        }
    }

}