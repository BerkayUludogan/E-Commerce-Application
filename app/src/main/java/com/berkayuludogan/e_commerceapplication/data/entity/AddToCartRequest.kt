package com.berkayuludogan.e_commerceapplication.data.entity

import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("ad") val name: String,
    @SerializedName("resim") val image: String,
    @SerializedName("kategori") val category: String,
    @SerializedName("fiyat") val price: Int,
    @SerializedName("marka") val brand: String,
    @SerializedName("siparisAdeti") val orderQuantity: Int = 1,
    @SerializedName("kullaniciAdi") val userName: String = Constants.USER_NAME,
)
