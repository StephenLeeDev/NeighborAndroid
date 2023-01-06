package com.stephenleedev.neighborandroid.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.stephenleedev.neighborandroid.util.extension.toDp

/**
 * Written by StephenLeeDev on 2022/12/25.
 */

object CommonBindingAdapter {

    @BindingAdapter(value = ["loadImage", "radius"], requireAll = false)
    @JvmStatic
    fun loadImage(view: ImageView, url: String, radius: Int = 0) {
        if (radius > 0) { // Function RoundedCorners returns an error when the radius is 0
            Glide.with(view.context)
                .load(url)
                .transform(
                    MultiTransformation(
                        CenterCrop(), RoundedCorners(radius.toDp)
                    )
                )
                .into(view)
        }
        else {
            Glide.with(view.context)
                .load(url)
                .transform(
                    MultiTransformation(
                        CenterCrop()
                    )
                )
                .into(view)
        }
    }

}