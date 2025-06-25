package com.berkayuludogan.e_commerceapplication.di

import com.berkayuludogan.e_commerceapplication.retrofit.APIUtils
import com.berkayuludogan.e_commerceapplication.retrofit.ECommerceDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideECommerceDao(): ECommerceDAO {
        return APIUtils.getECommerceDao()
    }
}