package com.berkayuludogan.e_commerceapplication.core.extensions

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImage(url: String, width: Int = 512, height: Int = 512) {
    Glide.with(this.context)
        .load(url)
        .override(width, height)
        .into(this)
}

fun Int.toCurrency(): String {
    return "%,d ₺".format(this).replace(',', '.')
}

fun View.showActionSnackbar(
    message: String,
    actionText: String,
    actionColor: Int,
    action: () -> Unit,
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionText) { action() }
        .setActionTextColor(ContextCompat.getColor(context, actionColor))
        .show()
}

