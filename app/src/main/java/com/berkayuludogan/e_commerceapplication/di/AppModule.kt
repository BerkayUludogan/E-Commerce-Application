package com.berkayuludogan.e_commerceapplication.di

import android.content.Context
import androidx.room.Room
import com.berkayuludogan.e_commerceapplication.data.local.FavoriteDAO
import com.berkayuludogan.e_commerceapplication.data.local.MyDatabase
import com.berkayuludogan.e_commerceapplication.retrofit.APIUtils
import com.berkayuludogan.e_commerceapplication.retrofit.ECommerceDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context: Context): FavoriteDAO {
        val db = Room.databaseBuilder(context, MyDatabase::class.java, "favorites.sqlite")
            .createFromAsset("favorites.sqlite")
            .build()
        return db.getFavoriteDao()
    }
}