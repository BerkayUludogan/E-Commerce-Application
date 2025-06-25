package com.berkayuludogan.e_commerceapplication.data.repository

import com.berkayuludogan.e_commerceapplication.data.datasource.ECommerceDataSource
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import javax.inject.Inject

class ECommerceRepository @Inject constructor(
    val eCommerceDataSource: ECommerceDataSource,
) {
    suspend fun fetchAllProducts(): List<Products> = eCommerceDataSource.fetchAllProducts()
}