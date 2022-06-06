package com.example.finalproject.ui.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentProfileBinding
import com.example.finalproject.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    private lateinit var fireBaseAuth: FirebaseAuth

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

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

        createNotificationChannel()

        binding.btnNotification.setOnClickListener {
            sendNotification()
        }

        return binding.root


    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val title = "Notification Title"
            val descriptionText = "Notification Description"
            val important = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, title, important).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val builder = activity?.let {
            NotificationCompat.Builder(it.applicationContext, CHANNEL_ID).setSmallIcon(
                R.drawable.icon_app
            )
                .setContentTitle("Kareem App")
                .setContentText("Akhi Jangan Lupa Dzikir Pagi")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        with(
            activity?.applicationContext?.let {
                NotificationManagerCompat.from(it)
            }
        ) {
            builder?.let { this?.notify(notificationId, it.build()) }
        }
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