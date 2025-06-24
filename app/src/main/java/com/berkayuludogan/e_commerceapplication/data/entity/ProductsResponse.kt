package com.berkayuludogan.e_commerceapplication.data.entity

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("urunler")
    val products: List<Products>,
    val success: Int,
)