package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }
}