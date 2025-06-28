package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    val eCommerceRepository: ECommerceRepository,
) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _isFavorite

    init {
        _isFavorite.value = false
    }

    fun toggleFavorite() {
        _isFavorite.value = _isFavorite.value != true
    }
}