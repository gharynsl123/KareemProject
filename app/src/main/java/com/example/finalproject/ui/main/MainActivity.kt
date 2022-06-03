package com.example.finalproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarTitle)
        binding.toolbarTitle.title= (binding.toolbarTitle.title)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView : BottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.quranFragment,
                R.id.homeFragment,
                R.id.profileFragment
            )
        )

        navView.setupWithNavController(navController)


    }
}