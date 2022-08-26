package com.example.finalproject.ui.petang

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.DzikirPetangResponseItem
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
        supportActionBar?.setDisplayShowTitleEnabled(false)

        _viewModel = ViewModelProvider(this).get(PetangViewModel::class.java)

        viewModel.apply {
            getData()
            petangResponse.observe(this@DzikirPetang) { showData(it) }
            isLoading.observe(this@DzikirPetang) { showLoading(it) }
            isError.observe(this@DzikirPetang) { showError(it) }
        }
    }

    private fun showData(data: List<DzikirPetangResponseItem>) {
        binding.apply {
            val mAdapter = DzikirPetangAdapter {}
            mAdapter.setData(data)
            rvDzikirPetang.layoutManager = LinearLayoutManager(this@DzikirPetang)
            rvDzikirPetang.adapter = mAdapter

            setSupportActionBar(toolbarTitle)
            toolbarTitle.title = (binding.toolbarTitle.title)
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                rvDzikirPetang.visibility = View.INVISIBLE
                progressMain.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvDzikirPetang.visibility = View.VISIBLE
                progressMain.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}