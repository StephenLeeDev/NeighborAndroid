package com.stephenleedev.neighborandroid.ui.auth.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stephenleedev.neighborandroid.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}