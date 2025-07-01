package com.berkayuludogan.e_commerceapplication.core.constants

import android.widget.ImageView
import com.bumptech.glide.Glide

object ApiPaths {
    const val BASE_URL = "http://kasimadalan.pe.hu/"
    const val IMAGE_BASE_URL = "http://kasimadalan.pe.hu/urunler/resimler/"
    const val FETCH_ALL_PRODUCTS = "urunler/tumUrunleriGetir.php"
    const val ADD_TO_CART = "urunler/sepeteUrunEkle.php"
    const val FETCH_ALL_CART_ITEMS = "urunler/sepettekiUrunleriGetir.php"
    const val DELETE_ITEM_TO_CART = "urunler/sepettenUrunSil.php"
}