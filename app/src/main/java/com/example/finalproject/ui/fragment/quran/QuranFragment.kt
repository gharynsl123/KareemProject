package com.example.finalproject.ui.fragment.quran

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.FragmentQuranBinding
import com.example.finalproject.ui.baca.BacaQuran
import com.example.finalproject.utils.quranutil.OnItemQuranClickCallback

class QuranFragment : Fragment() {

    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding

    //view
    private var _viewModel: QuranViewModel? = null
    private val viewModel get() = _viewModel as QuranViewModel


    private val quranAdapter by lazy { QuranAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = QuranAdapter()
        binding.rvQuran.layoutManager = LinearLayoutManager(activity)
        binding.rvQuran.adapter = mAdapter
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

        _viewModel = ViewModelProvider(this).get(QuranViewModel::class.java)

        viewModel.apply {
            getData()
            activity?.let { it ->
                quranResponse.observe(it) { mAdapter.setData(it) }
                isLoading.observe(it) { showLoading(it) }
                isError.observe(it) { showError(it) }
            }
            quranResponse.observe(viewLifecycleOwner) {
                mAdapter.setData(it)
                Log.d("Hasil", it?.toString()!!)
            }
            setupSearchView()
        }
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("ShowData", query!!)
                query.let {
                    viewModel.searchQuranByQuery(query)
                }

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })
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
