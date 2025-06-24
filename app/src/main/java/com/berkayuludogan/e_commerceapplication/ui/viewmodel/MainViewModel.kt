package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val eCommerceRepository: ECommerceRepository,
) : ViewModel() {
}