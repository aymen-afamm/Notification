package com.example.notif

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokenTextView = findViewById<TextView>(R.id.tokenTextView)

        // Récupérer le token FCM
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d(TAG, "FCM Token: $token")
                tokenTextView.text = "Token: $token"
            } else {
                Log.e(TAG, "Erreur lors de la récupération du token", task.exception)
                tokenTextView.text = "Erreur: Impossible de récupérer le token"
            }
        }
    }
}