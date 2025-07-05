package com.berkayuludogan.e_commerceapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.constants.ApiPaths
import com.berkayuludogan.e_commerceapplication.core.extensions.loadImage
import com.berkayuludogan.e_commerceapplication.core.extensions.showActionSnackbar
import com.berkayuludogan.e_commerceapplication.core.extensions.toCurrency
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.databinding.FavoriteCardDesignBinding
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(
    private val mContext: Context,
    private val favoriteList: List<Products>,
    private val onFavoriteClicked: (Int) -> Unit,
) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewCardDesign>() {
    inner class FavoriteViewCardDesign(val binding: FavoriteCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewCardDesign {
        val binding =
            FavoriteCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FavoriteViewCardDesign(binding)
    }


    override fun onBindViewHolder(holder: FavoriteViewCardDesign, position: Int) {
        val favorite = favoriteList[position]
        val design = holder.binding
        val imageUrl = "${ApiPaths.IMAGE_BASE_URL}/${favorite.image}"
        design.productImageView.loadImage(imageUrl)
        design.productBrand.text = favorite.brand
        design.productName.text = favorite.name
        design.productPrice.text = favorite.price.toCurrency()
        design.imageViewFavorite.setImageResource(R.drawable.heart)
        design.imageViewFavorite.setOnClickListener {
            it.showActionSnackbar(
                message = mContext.getString(R.string.snackbar_discard_message, favorite.name),
                actionText = mContext.getString(R.string.snackbar_yes),
                actionColor = R.color.errorColor
            )
            {
                onFavoriteClicked(favorite.id)
            }
        }
    }

    override fun getItemCount(): Int = favoriteList.size
}