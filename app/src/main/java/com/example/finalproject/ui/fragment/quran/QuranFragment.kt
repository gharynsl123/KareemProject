package com.example.finalproject.ui.fragment.quran

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.quranres.AyahsItem
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.FragmentQuranBinding
import com.example.finalproject.ui.baca.BacaQuran
import com.example.finalproject.utils.OnItemQuranClickCallback

class QuranFragment : Fragment(){

    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding


    private var _viewModel: QuranViewModel? = null
    private val viewModel get() = _viewModel as QuranViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
//initi
        _viewModel = ViewModelProvider(this).get(QuranViewModel::class.java)

        viewModel.apply {
            getData()
            activity?.let {
                quranResponse.observe(it) { showData(it) }
                isLoading.observe(it){ showLoading(it) }
                isError.observe(it) { showError(it) }
            }
        }
        setupSearchView()

        return binding.root
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty()) viewModel.searchQuranByQuery(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    if (query.isEmpty()) {
                        binding.apply {
                            viewModel.searchList.value = null
                        }
                    }
                }
                return true
            }

        })
    }

    private fun showData(data: List<SurahsItem>?) {
        binding.rvQuran.apply {
            val mAdapter = QuranAdapter()
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
                progressMain.visibility = View.VISIBLE
                rvQuran.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressMain.visibility = View.INVISIBLE
                rvQuran.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }

}
