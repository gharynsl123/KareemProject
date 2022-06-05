package com.example.finalproject.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityRegisterBinding
import com.example.finalproject.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding as ActivityRegisterBinding

    private lateinit var prossesDialog: ProgressDialog

    private lateinit var fireBaseAuth: FirebaseAuth

    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prossesDialog = ProgressDialog(this)

        prossesDialog.apply {
            setTitle("Please Wait")
            setMessage("Creating An Account ...")
            setCanceledOnTouchOutside(false)
        }
        fireBaseAuth = FirebaseAuth.getInstance()
        binding.btnReg.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        email = binding.tvInputEmail.text.toString().trim()
        password = binding.tvInputSandi.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tvInputEmail.error = "Invalid Email Formated"
        } else if (TextUtils.isEmpty(password)) {
            binding.tvInputSandi.error = "Please Enter password"
        } else if (password.length <= 6) {
            binding.tvInputSandi.error = "Password must bee 6 chara atleast "
        } else {
            fireBaseRegister()
        }
    }

    private fun fireBaseRegister() {
        prossesDialog.show()
        fireBaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                prossesDialog.dismiss()
                val firebaseUser = fireBaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account Created with email $email", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
            .addOnFailureListener {
                prossesDialog.dismiss()
                Toast.makeText(this, "Register Failed due to ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
