package com.berkayuludogan.e_commerceapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorites")
    suspend fun fetchAllFavorite(): List<Favorites>

    @Insert
    suspend fun addFavorite(favorite: Favorites)

    @Query("DELETE FROM Favorites WHERE productId = :productId")
    suspend fun removeFavorite(productId: Int)

    @Query("DELETE FROM Favorites")
    suspend fun deleteAllFavorites()
}