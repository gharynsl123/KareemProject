package com.example.finalproject.ui.baca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.data.response.quranres.AyahsItem
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.ActivityBacaQuranBinding
import com.example.finalproject.ui.fragment.quran.QuranAdapter
import com.example.finalproject.utils.OnItemQuranClickCallback

class BacaQuran : AppCompatActivity() {

    private var _binding: ActivityBacaQuranBinding? = null
    private val binding get() = _binding as ActivityBacaQuranBinding

    private var _viewModel: BacaanViewModel? = null
    private val viewModel get() = _viewModel as BacaanViewModel

    companion object {
        const val SURAH_DATA = "data"
        const val AYAT_DATA = "ayat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBacaQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this).get(BacaanViewModel::class.java)

        viewModel.apply {
            getData()
            bacaanResponse.observe(this@BacaQuran) { showData(it) }
            isLoading.observe(this@BacaQuran) { showLoading(it) }
            isError.observe(this@BacaQuran) { showError(it) }

        }

        val title = intent.getParcelableExtra<SurahsItem>(SURAH_DATA)
        val surah = intent.getParcelableExtra<AyahsItem>(AYAT_DATA)

        title?.let {
            binding.apply {
                titleSurah.text = title.englishName
                titleMeining.text = title.englishNameTranslation
            }
        }
        surah?.let {
            binding.apply {

            }
        }
    }

    private fun showData(data: List<AyahsItem>?) {
        binding.rvBacaQuran.apply {
            val mAdapter = BacaanQuranAdapter()
            mAdapter.setData(data)
            layoutManager = LinearLayoutManager(this@BacaQuran)
            adapter = mAdapter
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                progressMain.visibility = View.VISIBLE
                rvBacaQuran.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressMain.visibility = View.INVISIBLE
                rvBacaQuran.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}