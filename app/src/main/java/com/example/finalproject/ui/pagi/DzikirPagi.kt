package com.example.finalproject.ui.pagi

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.DzikirPagiResponseItem
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

        supportActionBar?.setDisplayShowTitleEnabled(false)

        _viewModel = ViewModelProvider(this).get(PagiViewModel::class.java)

        viewModel.apply {
            getData()
            pagiResponse.observe(this@DzikirPagi) { showData(it) }
            isLoading.observe(this@DzikirPagi) { showLoading(it) }
            isError.observe(this@DzikirPagi) { showError(it) }
        }
    }

    private fun showData(data: List<DzikirPagiResponseItem>) {
        binding.apply {
            val mAdapter = DzikirPagiAdapter {}
            mAdapter.setData(data)
            rvDzikirPagi.layoutManager = LinearLayoutManager(this@DzikirPagi)
            rvDzikirPagi.adapter = mAdapter

            setSupportActionBar(toolbarTitle)
            toolbarTitle.title = (binding.toolbarTitle.title)
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