package com.berkayuludogan.e_commerceapplication.retrofit

import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.data.entity.AddToCartRequest
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCartResponse
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ECommerceDAO {
    @GET(ApiPaths.FETCH_ALL_PRODUCTS)
    suspend fun fetchAllProducts(): ProductsResponse

    @POST(ApiPaths.FETCH_ALL_CART_ITEMS)
    @FormUrlEncoded
    suspend fun fetchAllCartItems(@Field("kullaniciAdi") userName: String): Response<ResponseBody>

    @POST(ApiPaths.ADD_TO_CART)
    @FormUrlEncoded
    suspend fun addProductToCart(
        @FieldMap fields: Map<String, @JvmSuppressWildcards Any>,
    ): CRUDResponse

    @POST(ApiPaths.DELETE_ITEM_TO_CART)
    @FormUrlEncoded
    suspend fun deleteItemToCart(
        @Field("sepetId") cartId: Int,
        @Field("kullaniciAdi") userName: String,
    ): CRUDResponse
}
