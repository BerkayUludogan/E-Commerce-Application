package com.berkayuludogan.e_commerceapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.core.extensions.loadImage
import com.berkayuludogan.e_commerceapplication.core.extensions.toCurrency
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.databinding.ProductsCardDesignBinding
import com.berkayuludogan.e_commerceapplication.ui.screens.MainScreenDirections

class ProductsAdapter(val mContext: Context, val productsList: List<Products>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsCardDesignHolder>() {

    inner class ProductsCardDesignHolder(val binding: ProductsCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCardDesignHolder {
        val binding =
            ProductsCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ProductsCardDesignHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductsCardDesignHolder, position: Int) {
        val product = productsList[position]
        val design = holder.binding
        val imageUrl = "${ApiPaths.IMAGE_BASE_URL}/${product.image}"

        design.nameText.text = product.name
        design.brandText.text = product.brand
        design.priceText.text = product.price.toCurrency()
        design.imageViewProd.loadImage(imageUrl)

        design.productsCardView.setOnClickListener {
            val details = MainScreenDirections.toProductDetailsScreen(product = product)
            it.findNavController().navigate(details)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}