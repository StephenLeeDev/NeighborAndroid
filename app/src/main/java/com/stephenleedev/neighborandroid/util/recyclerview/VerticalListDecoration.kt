package com.stephenleedev.neighborandroid.util.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.util.extension.toDp

/**
 * Written by StephenLeeDev on 2022/12/28.
 */

class VerticalListDecoration(
    private val paddingTop: Int = 10,
    private val paddingBottom: Int = 10,
    private val paddingLeft: Int = 10,
    private val paddingRight: Int = 10,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        // First item of list
        if (position == 0) {
            outRect.apply {
                top = paddingTop.toDp
                bottom = paddingBottom.toDp
                left = paddingLeft.toDp
                right = paddingRight.toDp
            }
        }
        else {
            outRect.apply {
                bottom = paddingBottom.toDp
                left = paddingLeft.toDp
                right = paddingRight.toDp
            }
        }

    }

}