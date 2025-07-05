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
import com.berkayuludogan.e_commerceapplication.core.myCartInterface.OnCartItemChangeListener
import com.berkayuludogan.e_commerceapplication.data.entity.ProductsCart
import com.berkayuludogan.e_commerceapplication.databinding.MyCartCardDesignBinding
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MyCartViewModel
import com.google.android.material.snackbar.Snackbar

class MyCartAdapter(
    private val mContext: Context,
    private var myCartProductList: List<ProductsCart>,
    private val viewModel: MyCartViewModel,
    private val listener: OnCartItemChangeListener,
) : RecyclerView.Adapter<MyCartAdapter.MyCartCardDesign>() {

    inner class MyCartCardDesign(var binding: MyCartCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartCardDesign {
        val binding = MyCartCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyCartCardDesign(binding)
    }

    override fun onBindViewHolder(holder: MyCartCardDesign, position: Int) {
        var myCartProduct = myCartProductList[position]
        val design = holder.binding

        val imageUrl = "${ApiPaths.IMAGE_BASE_URL}/${myCartProduct.image}"


        design.productImageView.loadImage(imageUrl)
        design.productName.text = myCartProduct.name
        design.productPrice.text = myCartProduct.price.toCurrency()
        design.productCount.text = myCartProduct.orderQuantity.toString()
        val initialCount = design.productCount.text.toString().toIntOrNull() ?: 1
        design.totalPrice.text = ((myCartProduct.price * initialCount)).toCurrency()
        design.trashImage.setOnClickListener {
            it.showActionSnackbar(
                message = mContext.getString(
                    R.string.snackbar_delete_cart_item_message,
                    myCartProduct.name
                ),
                actionText = mContext.getString(R.string.snackbar_yes),
                actionColor = R.color.errorColor
            )
            {
                viewModel.deleteItemToCart(myCartProduct.cartId)
            }
        }


        design.btnMinus.setOnClickListener {
            val currentCount = design.productCount.text.toString().toIntOrNull() ?: 1
            decrement(currentCount, myCartProduct.price)
            val (newCount, newTotalPrice) = decrement(currentCount, myCartProduct.price)
            design.productCount.text = newCount.toString()
            design.totalPrice.text = newTotalPrice
            myCartProductList[position].orderQuantity = newCount
            notifyItemChanged()
        }
        design.btnPlus.setOnClickListener {
            val currentCount = design.productCount.text.toString().toIntOrNull() ?: 1
            increment(currentCount, myCartProduct.price)
            val (newCount, newTotalPrice) = increment(currentCount, myCartProduct.price)
            design.productCount.text = newCount.toString()
            design.totalPrice.text = newTotalPrice
            myCartProductList[position].orderQuantity = newCount

            notifyItemChanged()

        }

    }


    override fun getItemCount(): Int {
        return myCartProductList.size
    }

    fun decrement(
        currentCount: Int,
        productionPrice: Int,
    ): Pair<Int, String> {
        val newCount = if (currentCount > 1) currentCount - 1 else 1
        val newTotalPrice = (productionPrice * newCount).toCurrency()
        return Pair(newCount, newTotalPrice)
    }

    fun increment(
        currentCount: Int,
        productionPrice: Int,
    ): Pair<Int, String> {
        val newCount = currentCount + 1
        val newTotalPrice = (productionPrice * newCount).toCurrency()
        return Pair(newCount, newTotalPrice)
    }

    private fun notifyItemChanged() {
        listener.onCartItemChanged()
    }

    fun getCartItems(): List<ProductsCart> = myCartProductList


}