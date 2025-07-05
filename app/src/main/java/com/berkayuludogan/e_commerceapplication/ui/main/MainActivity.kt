package com.berkayuludogan.e_commerceapplication.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.core.navigation.BottomNavigationBar
import com.berkayuludogan.e_commerceapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigationBar: BottomNavigationBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController

        bottomNavigationBar = BottomNavigationBar(navController, binding)
        bottomNavigationBar.setup()


    }
}