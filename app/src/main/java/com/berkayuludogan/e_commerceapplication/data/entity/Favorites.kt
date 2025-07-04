package com.berkayuludogan.e_commerceapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val productId: Int,
)
