package com.berkayuludogan.e_commerceapplication.core.constants

import android.widget.ImageView
import com.bumptech.glide.Glide

object ApiPaths {
    const val BASE_URL = "http://kasimadalan.pe.hu/"
    const val GET_ALL_PRODUCTS = "urunler/tumUrunleriGetir.php"
    const val ADD_TO_CART = "urunler/sepeteUrunEkle.php"
    const val IMAGES = "urunler/resimler/"

    fun getImageUrl(imageName: String): String {
        return "$BASE_URL/$IMAGES/$imageName"
    }

}