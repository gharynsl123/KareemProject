package com.example.finalproject.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.finalproject.ui.fragment.ProfileFragment
import com.example.finalproject.databinding.ActivityLoginBinding
import com.example.finalproject.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding as ActivityLoginBinding

    private lateinit var prossesDialog : ProgressDialog

    private lateinit var fireBaseAuth : FirebaseAuth

    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prossesDialog = ProgressDialog(this)

        prossesDialog.apply {
            setTitle("Please Wait")
            setMessage("Logging In...")
            setCanceledOnTouchOutside(false)
        }

        fireBaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLog.setOnClickListener {
            validateDate()
        }
    }

    private fun validateDate() {
        email = binding.tvInputEmail.text.toString().trim()
        password = binding.tvInputSandi.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tvInputEmail.error = "Invalid Email Formated"
        }else if (TextUtils.isEmpty(password)){
            binding.tvInputSandi.error = "Please Enter password"
        }else{
            fireBaseLogin()
        }
    }

    private fun fireBaseLogin() {
        prossesDialog.show()
        fireBaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                prossesDialog.dismiss()
                val firebaseUser = fireBaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Succesful Login as $email", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ProfileFragment::class.java))
                finish()
            }
            .addOnFailureListener {
                prossesDialog.dismiss()
                Toast.makeText(this, "Login Failed due to ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = fireBaseAuth.currentUser
        if (firebaseUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}