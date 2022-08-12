package com.example.finalproject.ui.pagi

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityDzikirPagiBinding

class DzikirPagi : AppCompatActivity() {

    private var _binding: ActivityDzikirPagiBinding? = null
    private val binding get() = _binding as ActivityDzikirPagiBinding


    private var _viewModel: PagiViewModel? = null
    private val viewModel get() = _viewModel as PagiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDzikirPagiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mAdapter = DzikirPagiAdapter {}

        binding.rvDzikirPagi.layoutManager = LinearLayoutManager(this@DzikirPagi)
        binding.rvDzikirPagi.adapter = mAdapter

        setSupportActionBar(binding.toolbarTitle)
        binding.toolbarTitle.title = (binding.toolbarTitle.title)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        _viewModel = ViewModelProvider(this).get(PagiViewModel::class.java)

        viewModel.apply {
            getData()
            pagiResponse.observe(this@DzikirPagi) { mAdapter.setData(it) }
            isLoading.observe(this@DzikirPagi) { showLoading(it) }
            isError.observe(this@DzikirPagi) { showError(it) }
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                progressMain.visibility = View.VISIBLE
                rvDzikirPagi.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressMain.visibility = View.INVISIBLE
                rvDzikirPagi.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}