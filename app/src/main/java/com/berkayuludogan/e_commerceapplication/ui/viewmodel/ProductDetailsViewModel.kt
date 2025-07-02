package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.constants.Constants
import com.berkayuludogan.e_commerceapplication.data.entity.AddToCartRequest
import com.berkayuludogan.e_commerceapplication.data.entity.CRUDResponse
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.http.Body
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite
    private var toast: Toast? = null
    private var cartJob: Job? = null

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
                eCommerceRepository.fetchAllCartItems(Constants.USER_NAME)

            } catch (e: Exception) {
                Log.e("Load Cart Items Error", "${e.message}")
            }
        }
    }

    suspend fun deleteItemToCart(cartId: Int) {
        viewModelScope.launch {
            try {
                eCommerceRepository.deleteItemToCart(cartId)

            } catch (e: Exception) {
                Log.e("Error deleting item", "${e.message}")
            }
        }
    }

    suspend fun addOrUpdateProductCart(productDetail: Products, context: Context) {
        val freshCartItems = eCommerceRepository.fetchAllCartItems(Constants.USER_NAME)
        val existsItems = freshCartItems.filter { it.name == productDetail.name }
        val totalCount = existsItems.sumOf { it.orderQuantity } + 1
        viewModelScope.launch(Dispatchers.Main) {
            existsItems.forEach {
                deleteItemToCart(it.cartId)
                Log.e("Sepet Silme", "$totalCount")
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
            orderQuantity = totalCount
        )
        toast?.cancel()  
        toast = Toast.makeText(context, context.getString(R.string.toastAddProduct), Toast.LENGTH_SHORT)
        toast?.show()
    }
    fun launchAddOrUpdate(productDetail: Products, context: Context) {
        cartJob?.cancel()
        cartJob = viewModelScope.launch {
            addOrUpdateProductCart(productDetail, context)
        }
    }

}






