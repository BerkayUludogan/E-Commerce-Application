package com.berkayuludogan.e_commerceapplication.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.databinding.MainScreenBinding
import com.berkayuludogan.e_commerceapplication.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment() {
    private lateinit var binding: MainScreenBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MainScreenBinding.inflate(inflater, container, false)

        return binding.root
    }
}