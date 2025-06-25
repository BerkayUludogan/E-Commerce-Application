package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eCommerceRepository: ECommerceRepository,
) : ViewModel() {
    init {
        fetchAllProducts()
    }

    fun fetchAllProducts() {
        viewModelScope.launch {
            val result = eCommerceRepository.fetchAllProducts()
            Log.e("Products Result", "$result")
        }
    }
}