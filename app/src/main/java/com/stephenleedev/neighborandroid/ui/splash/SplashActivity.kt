package com.stephenleedev.neighborandroid.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.stephenleedev.neighborandroid.databinding.ActivitySplashBinding
import com.stephenleedev.neighborandroid.domain.model.splash.SplashState
import com.stephenleedev.neighborandroid.ui.auth.SignInActivity
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.viewmodel.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObservers()

        lifecycleScope.launch {
            delay(1500)

            splashViewModel.getUserAccessToken()
        }
    }

    private fun initObservers() {
        splashViewModel.splashState.observe(this@SplashActivity) { state ->
            when (state) {
                is SplashState.Empty -> {
                    startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
                    finish()
                }
                is SplashState.Success -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
                else -> {}
            }
        }
    }
}