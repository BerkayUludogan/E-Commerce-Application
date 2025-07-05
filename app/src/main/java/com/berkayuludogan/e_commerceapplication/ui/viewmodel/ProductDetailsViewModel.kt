package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : FavoriteHandlerViewModel(eCommerceRepository) {

    private var toast: Toast? = null
    private var cartJob: Job? = null
    val currentCount = MutableLiveData<Int>().apply { value = 1 }
    val pricePerItem = MutableLiveData<Int>().apply { value = 0 }

    val totalPrice: LiveData<Int> = MediatorLiveData<Int>().apply {
        fun update() {
            val count = currentCount.value ?: 1
            val price = pricePerItem.value ?: 0
            value = count * price
        }
        addSource(currentCount) { update() }
        addSource(pricePerItem) { update() }
    }

    init {
        loadCartItems()
        fetchAllFavorite()

    }

    private fun addProductToCart(
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

    private fun loadCartItems() {
        viewModelScope.launch {
            try {
                eCommerceRepository.fetchAllCartItems()

            } catch (e: Exception) {
                Log.e("Load Cart Items Error", "${e.message}")
            }
        }
    }

    private fun deleteItemToCart(cartId: Int) {
        viewModelScope.launch {
            try {
                eCommerceRepository.deleteItemToCart(cartId)

            } catch (e: Exception) {
                Log.e("Error deleting item", "${e.message}")
            }
        }
    }

    private suspend fun addOrUpdateProductCart(
        productDetail: Products,
        context: Context,
        newQuantity: Int,
    ) {
        val freshCartItems = eCommerceRepository.fetchAllCartItems()
        val existsItems = freshCartItems.filter { it.name == productDetail.name }
        val existingQuantity = existsItems.sumOf { it.orderQuantity }
        val updatedQuantity = existingQuantity + newQuantity
        viewModelScope.launch(Dispatchers.Main) {
            existsItems.forEach {
                deleteItemToCart(it.cartId)
                delay(300)
            }
        }
        loadCartItems()
        addProductToCart(
            name = productDetail.name,
            image = productDetail.image,
            category = productDetail.category,
            price = productDetail.price,
            brand = productDetail.brand,
            orderQuantity = updatedQuantity
        )
        toast?.cancel()
        toast =
            Toast.makeText(context, context.getString(R.string.toastAddProduct), Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun launchAddOrUpdate(productDetail: Products, context: Context, newQuantity: Int) {
        cartJob?.cancel()
        cartJob = viewModelScope.launch {
            addOrUpdateProductCart(productDetail, context, newQuantity)
        }
    }

}






