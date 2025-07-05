package com.berkayuludogan.e_commerceapplication.ui.viewmodel

import com.berkayuludogan.e_commerceapplication.data.repository.ECommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    eCommerceRepository: ECommerceRepository,
) : FavoriteHandlerViewModel(eCommerceRepository) {
    init {
        fetchAllFavorite()
    }

    fun deleteFavorite(productId: Int) {


    }
}