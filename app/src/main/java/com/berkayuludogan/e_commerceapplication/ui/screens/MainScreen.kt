package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.berkayuludogan.e_commerceapplication.databinding.MainScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.adapter.ProductsAdapter
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)

        adapter = ProductsAdapter(
            requireContext(),
            emptyList(),
            emptyList(),
            onFavoriteClicked = { productId ->
                viewModel.toggleFavorite(productId)
            }
        )
        binding.recyclerViewProducts.adapter = adapter

        viewModel.productsList.observe(viewLifecycleOwner) { products ->
            val favorites = viewModel.favoriteList.value ?: emptyList()


            adapter.updateData(products, favorites)
        }

        viewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->
            val products = viewModel.productsList.value ?: emptyList()
            adapter.updateData(products, favorites)
        }

        binding.recyclerViewProducts.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.search(it)
                }
                return true
            }
        })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAllFavorite()
    }

}