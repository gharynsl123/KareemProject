package com.example.finalproject.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.FragmentHomeBinding
import com.example.finalproject.ui.baca.BacaQuran
import com.example.finalproject.ui.pagi.DzikirPagi
import com.example.finalproject.ui.petang.DzikirPetang
import com.example.finalproject.utils.quranutil.OnItemQuranClickCallback

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    //

    private var _viewModel: AmmaViewModel? = null
    private val viewModel get() = _viewModel as AmmaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this).get(AmmaViewModel::class.java)

        viewModel.apply {
            getData()
            activity?.let {
                quranResponse.observe(it) { showData(it) }
                isLoading.observe(it) { showLoading(it) }
                isError.observe(it) { showError(it) }
            }
        }

        binding.dzikirPagi.setOnClickListener {
            activity?.let {
                val intent = Intent(it, DzikirPagi::class.java)
                it.startActivity(intent)
            }
        }

        binding.dzikirPetang.setOnClickListener {
            activity?.let {
                val intent = Intent(it, DzikirPetang::class.java)
                it.startActivity(intent)
            }
        }
        return binding.root


    }


    private fun showData(data: List<SurahsItem>?) {
        binding.rvHome.apply {
            val mAdapter = AmmaAdapter()
            mAdapter.setData(data)
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter

            mAdapter.setOnItemClickCallback(object : OnItemQuranClickCallback {
                override fun onItemClicked(item: SurahsItem) {
                    startActivity(
                        Intent(
                            activity,
                            BacaQuran::class.java
                        ).putExtra(BacaQuran.SURAH_DATA, item)
                    )
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                rvHome.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                rvHome.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }


}