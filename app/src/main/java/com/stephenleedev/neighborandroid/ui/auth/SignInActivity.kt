package com.stephenleedev.neighborandroid.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stephenleedev.neighborandroid.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}