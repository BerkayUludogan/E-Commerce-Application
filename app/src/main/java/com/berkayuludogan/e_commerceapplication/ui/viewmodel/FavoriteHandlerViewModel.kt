package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class FavoriteHandlerViewModel(
    private val eCommerceRepository: ECommerceRepository,
) : ViewModel() {

    private val _favoriteList = MutableLiveData<List<Favorites>>()
    val favoriteList: LiveData<List<Favorites>> get() = _favoriteList

    fun fetchAllFavorite() {
        viewModelScope.launch {
            _favoriteList.value = eCommerceRepository.fetchAllFavorite()

        }
    }

    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            val currentFavorites = favoriteList.value ?: emptyList()
            val isCurrentlyFavorite = currentFavorites.any { it.productId == productId }
            if (isCurrentlyFavorite) {
                deleteFromFavorite(productId)
            } else {
                addToFavorite(productId)
            }
            fetchAllFavorite()
        }
    }

    private suspend fun addToFavorite(productId: Int) {
        eCommerceRepository.addToFavorite(productId)
    }

    private suspend fun deleteFromFavorite(productId: Int) {
        eCommerceRepository.deleteFromFavorite(productId)
    }
}