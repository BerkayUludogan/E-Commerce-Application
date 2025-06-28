package com.berkayuludogan.e_commerceapplication.retrofit

import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths

class APIUtils {
    companion object {
        fun getECommerceDao(): ECommerceDAO {
            return RetrofitClient.getClient(ApiPaths.BASE_URL).create(ECommerceDAO::class.java)
        }
    }
}