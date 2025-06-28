package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.core.extensions.loadImage
import com.berkayuludogan.e_commerceapplication.databinding.ProductDetailsScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsScreen : Fragment() {
    private lateinit var binding: ProductDetailsScreenBinding
    private lateinit var viewModel: ProductDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailsViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ProductDetailsScreenBinding.inflate(inflater, container, false)

        val bundle: ProductDetailsScreenArgs by navArgs()
        val productDetail = bundle.product
        val imageUrl = ApiPaths.getImageUrl(productDetail.image)
        viewModel.favorite.observe(viewLifecycleOwner) { isFav ->
            val iconRes = if (isFav) R.drawable.heart else R.drawable.heart_border
            binding.imageViewFavorite.setImageResource(iconRes)
        }



        binding.brandNameText.text = productDetail.brand
        binding.productImageView.loadImage(imageUrl)
        binding.productNameText.text = productDetail.name
        binding.productPriceText.text = "${productDetail.price} TL"

        binding.imageViewFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }



        return binding.root
    }


}