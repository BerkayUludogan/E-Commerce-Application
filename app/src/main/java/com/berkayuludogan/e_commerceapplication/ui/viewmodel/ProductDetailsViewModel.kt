package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.berkayuludogan.e_commerceapplication.data.entity.AddToCartRequest
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Body
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : ViewModel() {
    private val _cartItems = MutableLiveData<List<ProductsCart>>()
    val cartItems: LiveData<List<ProductsCart>> = _cartItems

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        _isFavorite.value = false
        loadCartItems()
    }

    fun addProductToCart(
        name: String,
        image: String,
        category: String,
        price: Int,
        brand: String,
        orderQuantity: Int,
    ) {
        viewModelScope.launch {
            eCommerceRepository.addProductToCart(
                name, image, category, price, brand, orderQuantity
            )
        }
    }

    fun toggleFavorite() {
        _isFavorite.value = _isFavorite.value != true
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                _cartItems.value = eCommerceRepository.fetchAllCartItems(Constants.USER_NAME)

            } catch (e: Exception) {
                Log.e("Load Cart Items Error", "${e.message}")
            }
        }
    }

    fun deleteItemToCart(cartId: Int) {
        viewModelScope.launch {
            try {
                eCommerceRepository.deleteItemToCart(cartId)

            } catch (e: Exception) {
                Log.e("Error deleting item", "${e.message}")
            }
        }
    }


}



