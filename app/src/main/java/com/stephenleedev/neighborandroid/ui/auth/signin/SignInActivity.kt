package com.stephenleedev.neighborandroid.ui.auth.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.stephenleedev.neighborandroid.databinding.ActivitySignInBinding
import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthRequest
import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthState
import com.stephenleedev.neighborandroid.domain.model.auth.SocialType
import com.stephenleedev.neighborandroid.ui.auth.signup.SignUpActivity
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.util.logFunctions
import com.stephenleedev.neighborandroid.viewmodel.auth.SocialAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    private val socialAuthViewModel: SocialAuthViewModel by viewModels()

    private var socialType: String = ""
    private var socialToken: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        initObservers()
    }

    private fun initViews() {
        initKakaoViews()
    }

    private fun initKakaoViews() {
        binding.kakaoLayout.setOnClickListener {
            startWithKakao()
        }
    }

    private fun initObservers() {
        socialAuthViewModel.socialAuthState.observe(this@SignInActivity) { state ->
            when (state) {
                is SocialAuthState.Exist -> {
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                }
                is SocialAuthState.None -> {
                    startActivity(Intent(this@SignInActivity, SignUpActivity::class.java).apply {
                        putExtra(SOCIAL_TYPE, socialType)
                        putExtra(SOCIAL_TOKEN, socialToken)
                    })
                }
                else -> {}
            }
        }
    }

    private fun startWithKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(
                this,
                callback = callback
            )
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                this,
                callback = callback
            )
        }
    }

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            logFunctions("로그인 실패 : $error")
        } else if (token != null) {
            UserApiClient.instance.me { user, errorString ->
                if (errorString != null) {
                    logFunctions("사용자 정보 요청 실패 : $errorString")
                } else if (user != null) {

                    logFunctions("성공 : $user")
                    logFunctions("token : $token")

                    socialType = SocialType.KAKAO.type
                    socialToken = token.accessToken

                    socialAuthViewModel.getIsSocialAccountExist(
                        body = SocialAuthRequest(
                            socialType = SocialType.KAKAO.type,
                            socialToken = token.accessToken
                        )
                    )
                }
            }
        }
    }

    companion object {
        const val SOCIAL_TYPE = "SOCIAL_TYPE"
        const val SOCIAL_TOKEN = "SOCIAL_TOKEN"
    }

}