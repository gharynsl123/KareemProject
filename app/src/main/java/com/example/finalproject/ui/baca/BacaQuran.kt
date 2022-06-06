package com.example.finalproject.ui.baca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.R
import com.example.finalproject.data.response.quranres.AyahsItem
import com.example.finalproject.data.response.quranres.QuranResponse
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.ActivityBacaQuranBinding

class BacaQuran : AppCompatActivity() {

    private var _binding : ActivityBacaQuranBinding? = null
    private val binding get() = _binding as ActivityBacaQuranBinding

    companion object{
        const val SURAH_DATA = "data"
        const val AYAT_DATA = "ayat"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBacaQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getParcelableExtra<SurahsItem>(SURAH_DATA)
        val surah = intent.getParcelableExtra<AyahsItem>(AYAT_DATA)

        title?.let {
            binding.apply {
                titleSurah.text = title.englishName
                titleMeining.text = title.englishNameTranslation
            }
        }
    }
}