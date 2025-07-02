package com.berkayuludogan.e_commerceapplication.data.entity

import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.google.gson.annotations.SerializedName

data class ProductsCart(
    @SerializedName(value = "sepetId")
    val cartId: Int,
    @SerializedName(value = "ad")
    val name: String,
    @SerializedName(value = "resim")
    val image: String,
    @SerializedName(value = "kategori")
    val category: String,
    @SerializedName(value = "fiyat")
    val price: Int,
    @SerializedName(value = "marka")
    val brand: String,
    @SerializedName(value = "siparisAdeti")
    var orderQuantity: Int,
    @SerializedName(value = "kullaniciAdi")
    val userName: String = Constants.USER_NAME,
)
