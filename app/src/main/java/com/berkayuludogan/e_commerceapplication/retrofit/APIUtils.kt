package com.berkayuludogan.e_commerceapplication.retrofit

import com.berkayuludogan.e_commerceapplication.core.ApiPaths

class APIUtils {
    companion object {
        fun getECommerceDao(): ECommerceDAO {
            return RetrofitClient.getClient(ApiPaths.BASE_URL).create(ECommerceDAO::class.java)
        }
    }
}