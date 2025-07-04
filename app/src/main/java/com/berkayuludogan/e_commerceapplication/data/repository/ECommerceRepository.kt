package com.berkayuludogan.e_commerceapplication.data.repository

import com.berkayuludogan.e_commerceapplication.data.datasource.ECommerceDataSource
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.local.FavoriteDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ECommerceRepository @Inject constructor(
    val eCommerceDataSource: ECommerceDataSource,
) {
    suspend fun fetchAllProducts(): List<Products> = eCommerceDataSource.fetchAllProducts()

    suspend fun fetchAllCartItems(userName: String): List<ProductsCart> =
        eCommerceDataSource.fetchAllCartItems(userName)

    suspend fun addProductToCart(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
    ): CRUDResponse =
        eCommerceDataSource.addProductToCart(name, image, category, price, brand, orderQuantity)

    suspend fun deleteItemToCart(
        cartId: Int,
    ): CRUDResponse = eCommerceDataSource.deleteItemToCart(cartId)

    suspend fun addToFavorite(productId: Int) =
        eCommerceDataSource.addToFavorite(productId)

    suspend fun deleteFromFavorite(productId: Int) =
        eCommerceDataSource.deleteFromFavorite(productId)

    suspend fun fetchAllFavorite(): List<Favorites> = eCommerceDataSource.fetchAllFavorite()
    suspend fun deleteAllFavorites() = eCommerceDataSource.deleteAllFavorites()
}