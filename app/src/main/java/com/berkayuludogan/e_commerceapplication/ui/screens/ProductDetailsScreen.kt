package com.berkayuludogan.e_commerceapplication.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

@AndroidEntryPoint
class ProductDetailsScreen : Fragment() {
    private lateinit var binding: ProductDetailsScreenBinding
    private lateinit var viewModel: ProductDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailsViewModel by viewModels()
        viewModel = tempViewModel
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ProductDetailsScreenBinding.inflate(inflater, container, false)

        val bundle: ProductDetailsScreenArgs by navArgs()
        val productDetail = bundle.product
        val imageUrl = "${ApiPaths.IMAGE_BASE_URL}/${productDetail.image}"

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFav ->
            val iconRes = if (isFav) R.drawable.heart else R.drawable.heart_border
            binding.imageViewFavorite.setImageResource(iconRes)
        }
        binding.brandNameText.text = productDetail.brand
        binding.productImageView.loadImage(imageUrl)
        binding.productNameText.text = productDetail.name
        binding.productPriceText.text = productDetail.price.toCurrency()

        binding.imageViewFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }

        binding.addCartButton.setOnClickListener {
            binding.addCartButton.isEnabled = false
            viewModel.launchAddOrUpdate(productDetail, requireContext())
            binding.addCartButton.isEnabled = true


        }
        return binding.root
    }


    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.bottomNavigationBar?.showBottomBar()
    }


}

