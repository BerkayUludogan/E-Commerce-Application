package com.berkayuludogan.e_commerceapplication.data.datasource

import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCartResponse
import com.berkayuludogan.e_commerceapplication.data.local.FavoriteDAO
import com.berkayuludogan.e_commerceapplication.retrofit.ECommerceDAO
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import okhttp3.ResponseBody

class ECommerceDataSource @Inject constructor(
    val eCommerceDAO: ECommerceDAO,
    val favoriteDAO: FavoriteDAO,
) {

    suspend fun fetchAllProducts(): List<Products> = withContext(Dispatchers.IO) {
        return@withContext eCommerceDAO.fetchAllProducts().products
    }

    suspend fun fetchAllCartItems(userName: String): List<ProductsCart> =
        withContext(Dispatchers.IO) {
            val response = eCommerceDAO.fetchAllCartItems(userName)
            if (!response.isSuccessful) return@withContext emptyList()

            val bodyString = response.body()?.string()
            if (bodyString.isNullOrBlank()) return@withContext emptyList()

            return@withContext Gson().fromJson(
                bodyString,
                ProductsCartResponse::class.java
            ).productsCart ?: emptyList()
        }

    suspend fun addProductToCart(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
        userName: String = Constants.USER_NAME,
    ): CRUDResponse {
        val params = mapOf(
            "ad" to name,
            "resim" to image,
            "kategori" to category,
            "fiyat" to price,
            "marka" to brand,
            "siparisAdeti" to orderQuantity,
            "kullaniciAdi" to userName
        )
        return eCommerceDAO.addProductToCart(params)
    }

    suspend fun deleteItemToCart(
        cartId: Int,
        userName: String = Constants.USER_NAME,
    ): CRUDResponse = eCommerceDAO.deleteItemToCart(cartId, userName)

    suspend fun addToFavorite(productId: Int) {
        val favorite = Favorites(0, Constants.USER_NAME, productId)
        return favoriteDAO.addFavorite(favorite)
    }

    suspend fun deleteFromFavorite(productId: Int) = favoriteDAO.removeFavorite(productId)

    suspend fun fetchAllFavorite(): List<Favorites> = withContext(Dispatchers.IO) {
        return@withContext favoriteDAO.fetchAllFavorite()
    }

    suspend fun deleteAllFavorites() = favoriteDAO.deleteAllFavorites()
}