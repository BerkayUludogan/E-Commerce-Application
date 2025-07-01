package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCartResponse
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCartViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : ViewModel() {
    private val _productCartList = MutableLiveData<List<ProductsCart>>()
    val productCartList: LiveData<List<ProductsCart>> = _productCartList

    init {
        fetchAllProducts(Constants.USER_NAME)
    }

    private fun fetchAllProducts(userName: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _productCartList.value = eCommerceRepository.fetchAllCartItems(userName)
            } catch (e: Exception) {
                Log.e("CartFetchError", "Gerçek hata: ${e.message}", e)
                _productCartList.value = emptyList()
            }
        }
    }

    fun deleteItemToCart(cartId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                eCommerceRepository.deleteItemToCart(cartId)
                fetchAllProducts(Constants.USER_NAME)
            } catch (e: Exception) {
                Log.e("Error deleting item", "${e.message}")
            }
        }
    }
}