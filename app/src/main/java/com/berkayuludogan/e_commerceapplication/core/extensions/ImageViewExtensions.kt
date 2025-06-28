package com.berkayuludogan.e_commerceapplication.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, width: Int = 512, height: Int = 512) {
    Glide.with(this.context)
        .load(url)
        .override(width, height)
        .into(this)
}