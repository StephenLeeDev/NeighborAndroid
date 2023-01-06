package com.stephenleedev.neighborandroid.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.ActivitySignUpBinding
import com.stephenleedev.neighborandroid.domain.model.auth.register.RegisterState
import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.UserThumbnailUpdateState
import com.stephenleedev.neighborandroid.ui.auth.signin.SignInActivity.Companion.SOCIAL_TOKEN
import com.stephenleedev.neighborandroid.ui.auth.signin.SignInActivity.Companion.SOCIAL_TYPE
import com.stephenleedev.neighborandroid.ui.auth.signup.apartment.SignUpApartmentFragment
import com.stephenleedev.neighborandroid.ui.auth.signup.guide.SignUpGuideFragment
import com.stephenleedev.neighborandroid.ui.auth.signup.profile.SignUpProfileFragment
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.util.file.FileUtil
import com.stephenleedev.neighborandroid.util.viewpager.PagerFragmentStateAdapter
import com.stephenleedev.neighborandroid.viewmodel.auth.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    private val registerViewModel: RegisterViewModel by viewModels()

    private val viewPagerAdapter = PagerFragmentStateAdapter(this@SignUpActivity)

    private val socialType by lazy { intent.getStringExtra(SOCIAL_TYPE) }
    private val socialToken by lazy { intent.getStringExtra(SOCIAL_TOKEN) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        initObservers()
    }

    private fun initViews() {
        initFragments()
        initButtonViews()
    }

    private fun initFragments() {
        binding.viewPager.apply {
            adapter =
                viewPagerAdapter.apply {
                    addFragment(SignUpGuideFragment())
                    addFragment(SignUpApartmentFragment())
                    addFragment(SignUpProfileFragment())

                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)

                            registerViewModel.setCurrentPage(page = position + 1)
                        }
                    })
                }

            isUserInputEnabled = false
        }
    }

    private fun initButtonViews() {
        binding.apply {
            buttonLayout.buttonPrevious.apply {
                setOnClickListener {
                    val currentPage = viewPager.currentItem

                    // Go back to the previous
                    if (currentPage > 0) {
                        viewPager.setCurrentItem(currentPage - 1, true)
                    }
                }
            }

            buttonLayout.buttonNext.apply {
                setOnClickListener {
                    val currentPage = viewPager.currentItem + 1

                    // Go to the next page
                    if (currentPage < SIGNUP_TOTAL_FRAGMENT_COUNT) {
                        viewPager.setCurrentItem(currentPage, true)
                    }

                    // Register user
                    else if (currentPage == SIGNUP_TOTAL_FRAGMENT_COUNT) {
                        registerViewModel.postAuthRegister(
                            socialType = socialType ?: return@setOnClickListener,
                            socialToken = socialToken ?: return@setOnClickListener
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        registerViewModel.apply {

            currentPage.observe(this@SignUpActivity) { page ->

                binding.buttonLayout.apply {

                    // Previous button is only invisible on first page
                    buttonPrevious.isVisible = page > 1

                    when (page) {
                        1 -> {
                            buttonNext.text = getString(R.string.get_started_signup)
                        }
                        2 -> {
                            buttonNext.text = getString(R.string.next)
                        }
                        3 -> {
                            buttonNext.text = getString(R.string.complete)
                        }
                    }
                }

                checkIsValid()
            }

            isValid.observe(this@SignUpActivity) { valid ->
                binding.buttonLayout.buttonNext.isEnabled = valid
            }

            selectedApartmentId.observe(this@SignUpActivity) {
                checkIsValid()
            }

            nickname.observe(this@SignUpActivity) {
                checkIsValid()
            }

            selectedPurposeIdList.observe(this@SignUpActivity) {
                checkIsValid()
            }

            registerState.observe(this@SignUpActivity) { state ->
                when (state) {
                    is RegisterState.Success -> {
                        val thumbnail: MultipartBody.Part? = thumbnailMultipart.value

                        // If the thumbnail has been registered, then update the user's thumbnail
                        if (thumbnail != null) {
                            patchUserThumbnail()
                        }
                        // If the thumbnail is not registered, then go to the main screen
                        else {
                            goToMainActivity()
                        }
                    }
                    else -> {}
                }
            }

        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
        finishAffinity()
    }

    companion object {
        const val SIGNUP_TOTAL_FRAGMENT_COUNT = 3
    }

}