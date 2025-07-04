package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayuludogan.e_commerceapplication.core.extensions.toCurrency
import com.berkayuludogan.e_commerceapplication.core.myCartInterface.OnCartItemChangeListener
import com.berkayuludogan.e_commerceapplication.databinding.MyCartScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.adapter.MyCartAdapter
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MyCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCartScreen : Fragment(), OnCartItemChangeListener {
    private lateinit var binding: MyCartScreenBinding
    private val viewModel: MyCartViewModel by viewModels()
    private lateinit var myCartAdapter: MyCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MyCartScreenBinding.inflate(inflater, container, false)

        viewModel.productCartList.observe(viewLifecycleOwner) {
            myCartAdapter = MyCartAdapter(requireContext(), it, viewModel, listener = this)
            binding.myCartRecyclerView.adapter = myCartAdapter
        }
        binding.myCartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.totalPrice.observe(viewLifecycleOwner) { total ->
            binding.totalPriceText.text = total.toCurrency()
        }

        return binding.root
    }

    override fun onCartItemChanged() {
        val currentList = myCartAdapter.getCartItems()
        viewModel.updateCartItem(currentList)
    }

}