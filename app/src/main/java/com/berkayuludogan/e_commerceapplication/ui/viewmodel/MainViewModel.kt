package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.data.entity.Favorites
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : FavoriteHandlerViewModel(eCommerceRepository) {
    private val _productsList = MutableLiveData<List<Products>>()
    val productsList: LiveData<List<Products>> = _productsList
    val combinedProductFavoriteList = MediatorLiveData<Pair<List<Products>, List<Favorites>>>()


    init {

        fetchAllProducts()

        combinedProductFavoriteList.addSource(productsList) { products ->
            val favorites = favoriteList.value ?: emptyList()
            combinedProductFavoriteList.value = Pair(products, favorites)
        }

        combinedProductFavoriteList.addSource(favoriteList) { favorites ->
            val products = productsList.value ?: emptyList()
            combinedProductFavoriteList.value = Pair(products, favorites)
        }

    }
    private fun fetchAllProducts() {
        viewModelScope.launch {
            _productsList.value = eCommerceRepository.fetchAllProducts()
        }
    }

}