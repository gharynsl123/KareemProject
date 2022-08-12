package com.example.finalproject.ui.petang

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityDzikirPetangBinding

class DzikirPetang : AppCompatActivity() {

    private var _binding: ActivityDzikirPetangBinding? = null
    private val binding get() = _binding as ActivityDzikirPetangBinding

    private var _viewModel: PetangViewModel? = null
    private val viewModel get() = _viewModel as PetangViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDzikirPetangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = DzikirPetangAdapter {}
        binding.rvDzikirPetang.layoutManager = LinearLayoutManager(this@DzikirPetang)
        binding.rvDzikirPetang.adapter = mAdapter
        setSupportActionBar(binding.toolbarTitle)
        binding.toolbarTitle.title = (binding.toolbarTitle.title)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        _viewModel = ViewModelProvider(this).get(PetangViewModel::class.java)

        viewModel.apply {
            getData()
            petangResponse.observe(this@DzikirPetang) { mAdapter.setData(it) }
            isLoading.observe(this@DzikirPetang) { showLoading(it) }
            isError.observe(this@DzikirPetang) { showError(it) }
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                progressMain.visibility = View.VISIBLE
                rvDzikirPetang.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressMain.visibility = View.INVISIBLE
                rvDzikirPetang.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}