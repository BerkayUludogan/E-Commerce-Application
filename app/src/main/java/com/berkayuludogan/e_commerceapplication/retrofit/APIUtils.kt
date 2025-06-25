package com.berkayuludogan.e_commerceapplication.retrofit

import com.berkayuludogan.e_commerceapplication.core.Constants

class APIUtils {
    companion object {
        fun getECommerceDao(): ECommerceDAO {
            return RetrofitClient.getClient(Constants.BASE_URL).create(ECommerceDAO::class.java)
        }
    }
}