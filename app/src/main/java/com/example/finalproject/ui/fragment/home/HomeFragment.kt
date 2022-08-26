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
import com.example.finalproject.ui.pagi.DzikirPagi
import com.example.finalproject.ui.petang.DzikirPetang

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private var _viewModel: AmmaViewModel? = null
    private val viewModel get() = _viewModel as AmmaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dzikirPagi.setOnClickListener {
                activity?.let {
                    val intent = Intent(it, DzikirPagi::class.java)
                    it.startActivity(intent)
                }
            }
            dzikirPetang.setOnClickListener {
                activity?.let {
                    val intent = Intent(it, DzikirPetang::class.java)
                    it.startActivity(intent)
                }
            }
        }

        _viewModel = ViewModelProvider(this).get(AmmaViewModel::class.java)

        viewModel.apply {
            getData()
            quranResponse.observe(viewLifecycleOwner) { showData(it) }
            isLoading.observe(viewLifecycleOwner) { showLoading(it) }
            isError.observe(viewLifecycleOwner) { showError(it) }
        }
    }
    private fun showData(list: List<SurahsItem>?) {
        binding.apply {
            val mAdapter = AmmaAdapter()
            mAdapter.setData(list)
            rvHome.layoutManager = LinearLayoutManager(activity)
            rvHome.adapter = mAdapter
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.rvHome.visibility = View.INVISIBLE
        } else {
            binding.rvHome.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}