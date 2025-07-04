package com.berkayuludogan.e_commerceapplication.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.core.extensions.loadImage
import com.berkayuludogan.e_commerceapplication.core.extensions.toCurrency
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.databinding.ProductDetailsScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.main.MainActivity
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class ProductDetailsScreen : Fragment() {
    private lateinit var binding: ProductDetailsScreenBinding
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ProductDetailsScreenBinding.inflate(inflater, container, false)

        val bundle: ProductDetailsScreenArgs by navArgs()
        val productDetail = bundle.product
        val imageUrl = "${ApiPaths.IMAGE_BASE_URL}/${productDetail.image}"

        viewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->
            val isFavorite = favorites.any { it.productId == productDetail.id }
            val iconRes =  if(isFavorite) R.drawable.heart else R.drawable.heart_border
            binding.imageViewFavorite.setImageResource(iconRes)
        }

        binding.brandNameText.text = productDetail.brand
        binding.productImageView.loadImage(imageUrl)
        binding.productNameText.text = productDetail.name
        binding.productPriceText.text = productDetail.price.toCurrency()
        viewModel.pricePerItem.value = productDetail.price

        viewModel.currentCount.observe(viewLifecycleOwner) {
            binding.productCount.text = it.toString()
        }
        viewModel.totalPrice.observe(viewLifecycleOwner) { total ->
            binding.productTotalPriceText.text = total.toCurrency()
        }
        binding.btnMinus.setOnClickListener {
            val newValue = (viewModel.currentCount.value ?: 1) - 1
            if (newValue >= 1) {
                viewModel.currentCount.value = newValue
            }
        }
        binding.btnPlus.setOnClickListener {
            val newValue = (viewModel.currentCount.value ?: 1) + 1
            viewModel.currentCount.value = newValue
            viewModel.fetchAllFavorite()
        }
        binding.imageViewFavorite.setOnClickListener {

            viewModel.toggleFavorite(productDetail.id)

        }
        binding.addCartButton.setOnClickListener {
            binding.addCartButton.isEnabled = false
            lifecycleScope.launch {
                viewModel.launchAddOrUpdate(
                    productDetail,
                    requireContext(),
                    viewModel.currentCount.value ?: 1
                )
                binding.addCartButton.isEnabled = true
            }
        }
        return binding.root
    }


    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.bottomNavigationBar?.showBottomBar()
    }


}

