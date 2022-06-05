package com.example.finalproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityBacaQuranBinding

class BacaQuran : AppCompatActivity() {

    private var _binding : ActivityBacaQuranBinding? = null
    private val binding get() = _binding as ActivityBacaQuranBinding

    companion object{
        const val EXTRA_DATA = "data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baca_quran)
    }
}