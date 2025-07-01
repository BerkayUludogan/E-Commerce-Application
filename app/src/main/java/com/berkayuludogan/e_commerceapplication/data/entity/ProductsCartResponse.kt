package com.berkayuludogan.e_commerceapplication.data.entity

import com.google.gson.annotations.SerializedName

data class ProductsCartResponse(
    @SerializedName("urunler_sepeti")
    val productsCart: List<ProductsCart>?,
    val success: String,
)
