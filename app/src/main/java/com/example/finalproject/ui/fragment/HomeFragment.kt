package com.example.finalproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentHomeBinding
import com.example.finalproject.ui.pagi.DzikirPagi
import com.example.finalproject.ui.petang.DzikirPetang

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

}