package com.berkayuludogan.e_commerceapplication.data.repository

import com.berkayuludogan.e_commerceapplication.data.datasource.ECommerceDataSource
import javax.inject.Inject

class ECommerceRepository @Inject constructor(
    val eCommerceDataSource: ECommerceDataSource,
) {
}