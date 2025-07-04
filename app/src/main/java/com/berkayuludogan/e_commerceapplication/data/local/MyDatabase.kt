package com.berkayuludogan.e_commerceapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites

@Database(entities = [Favorites::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDAO
}