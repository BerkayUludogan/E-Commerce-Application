package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayuludogan.e_commerceapplication.databinding.FavoriteScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.adapter.FavoriteAdapter
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.FavoriteViewModel
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteScreen : Fragment() {
    private lateinit var binding: FavoriteScreenBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FavoriteScreenBinding.inflate(inflater, container, false)

        mainViewModel.productsList.observe(viewLifecycleOwner) { allProducts ->
            favoriteViewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->
                val favoriteProduct = allProducts.filter { product ->
                    favorites.any { it.productId == product.id }

                }
                if (favoriteProduct.isEmpty()) {
                    binding.favoriteRecyclerView.visibility = View.GONE
                    binding.emptyTextView.visibility = View.VISIBLE
                } else {
                    binding.favoriteRecyclerView.visibility = View.VISIBLE
                    binding.emptyTextView.visibility = View.GONE

                }
                binding.favoriteRecyclerView.adapter =
                    FavoriteAdapter(requireContext(), favoriteProduct, onFavoriteClicked = {
                        favoriteViewModel.toggleFavorite(it)
                    })
                binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            }

        }
        return binding.root
    }


}