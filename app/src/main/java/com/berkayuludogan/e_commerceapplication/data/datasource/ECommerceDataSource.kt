package com.berkayuludogan.e_commerceapplication.data.datasource

import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.retrofit.ECommerceDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ECommerceDataSource @Inject constructor(
    val eCommerceDAO: ECommerceDAO,
) {

    suspend fun fetchAllProducts(): List<Products> = withContext(Dispatchers.IO) {
        return@withContext eCommerceDAO.fetchAllProducts().products
    }


}