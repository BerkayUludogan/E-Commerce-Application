package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.berkayuludogan.e_commerceapplication.databinding.MyCartScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.adapter.MyCartAdapter
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MyCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCartScreen : Fragment() {
    private lateinit var binding: MyCartScreenBinding
    private lateinit var viewModel: MyCartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MyCartViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MyCartScreenBinding.inflate(inflater, container, false)

        viewModel.productCartList.observe(viewLifecycleOwner) {
            val myCartAdapter = MyCartAdapter(requireContext(), it, viewModel)
            binding.myCartRecyclerView.adapter = myCartAdapter
        }

        binding.myCartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}