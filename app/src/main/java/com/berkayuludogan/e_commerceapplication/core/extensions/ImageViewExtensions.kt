package com.berkayuludogan.e_commerceapplication.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage(url: String, width: Int = 512, height: Int = 512) {
    Glide.with(this.context)
        .load(url)
        .transform(RoundedCorners(24))
        .override(width, height)
        .into(this)
}