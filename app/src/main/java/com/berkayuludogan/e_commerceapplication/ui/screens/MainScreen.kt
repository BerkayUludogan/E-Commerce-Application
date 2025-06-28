package com.berkayuludogan.e_commerceapplication.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.data.entity.Products
import com.berkayuludogan.e_commerceapplication.databinding.MainScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.adapter.ProductsAdapter
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)

        viewModel.productsList.observe(viewLifecycleOwner) {
            val productsAdapter = ProductsAdapter(requireContext(), it)
            binding.recyclerViewProducts.adapter = productsAdapter
        }
        binding.recyclerViewProducts.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }
}