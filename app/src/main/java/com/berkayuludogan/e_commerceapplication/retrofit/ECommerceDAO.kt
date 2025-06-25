package com.berkayuludogan.e_commerceapplication.retrofit

import com.berkayuludogan.e_commerceapplication.core.ApiPaths
import com.berkayuludogan.e_commerceapplication.data.entity.AddToCartRequest
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ECommerceDAO {
    @GET(ApiPaths.GET_ALL_PRODUCTS)
    suspend fun fetchAllProducts(): ProductsResponse

    @POST(ApiPaths.ADD_TO_CART)
    @FormUrlEncoded
    suspend fun addProductToCart(@Body request: AddToCartRequest): CRUDResponse
}
