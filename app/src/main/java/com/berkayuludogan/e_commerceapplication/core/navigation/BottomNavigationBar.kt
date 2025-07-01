package com.berkayuludogan.e_commerceapplication.core.navigation

import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import com.berkayuludogan.e_commerceapplication.R
import com.berkayuludogan.e_commerceapplication.databinding.ActivityMainBinding

class BottomNavigationBar(
    private val navController: NavController,
    private val binding: ActivityMainBinding,
) {

    fun setup() {
        setupClickListener()
        setupDestinationListener()
    }

    private fun setupClickListener() {
        binding.navHome.setOnClickListener {
            navigateIfNotCurrent(R.id.mainScreen)
        }
        binding.navMyCart.setOnClickListener {
            navigateIfNotCurrent(R.id.myCartScreen)

        }

    }

    private fun navigateIfNotCurrent(destinationId: Int) {
        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(destinationId)
        }
    }

    private fun setupDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainScreen -> {
                    updateBottomBarIcons(R.id.nav_home)
                }
                R.id.myCartScreen -> {
                    updateBottomBarIcons(R.id.nav_my_cart)

                }

                R.id.productDetailsScreen -> {
                    hideBottomBar()
                }

                else -> {
                    showBottomBar()
                }
            }
        }
    }

    private fun updateBottomBarIcons(activeId: Int) {
        val allButtons = listOf(
            binding.navHome to R.drawable.home,
            binding.navMyCart to R.drawable.my_cart,
            binding.navFavorite to R.drawable.heart_border
        )
        allButtons.forEach { (view, iconRes) ->
            val imageView = view.getChildAt(0) as ImageView
            if (view.id == activeId) {
                imageView.setImageResource(getActiveIcon(iconRes))
            } else {
                imageView.setImageResource(iconRes)
            }
        }
    }

    private fun getActiveIcon(normalIcon: Int): Int {
        return when (normalIcon) {
            R.drawable.home -> R.drawable.home_selected
            R.drawable.my_cart -> R.drawable.my_cart_selected
            R.drawable.heart_border -> R.drawable.heart
            else -> normalIcon
        }
    }

    private fun hideBottomBar() {
        binding.customBottomBar.animate()
            .translationY(binding.customBottomBar.height.toFloat())
            .setDuration(200)
            .withEndAction {
                binding.customBottomBar.visibility = View.INVISIBLE  // GONE değil, INVISIBLE
            }
            .start()
    }

    fun showBottomBar() {
        binding.customBottomBar.apply {
            visibility = View.VISIBLE
            translationY = height.toFloat()
            animate()
                .translationY(0f)
                .setDuration(200)
                .start()
        }
    }

}