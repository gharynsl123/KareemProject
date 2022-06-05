package com.example.finalproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentProfileBinding
import com.example.finalproject.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    private lateinit var fireBaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        fireBaseAuth = FirebaseAuth.getInstance()
        chekUser()

        binding.btnLogOut.setOnClickListener {
            fireBaseAuth.signOut()
            chekUser()

        }
        return binding.root


    }

    private fun chekUser() {
        val firebaseUser = fireBaseAuth.currentUser
        Log.i("ProfileFragment", "$firebaseUser")
        if (firebaseUser != null) {
            val email = firebaseUser.email
            binding.userEmail.text = email
        } else {
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }
        }
    }

}