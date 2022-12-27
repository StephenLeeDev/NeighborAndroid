package com.stephenleedev.neighborandroid.ui.auth.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.stephenleedev.neighborandroid.databinding.ActivitySignUpBinding
import com.stephenleedev.neighborandroid.ui.auth.signup.apartment.SignUpApartmentFragment
import com.stephenleedev.neighborandroid.ui.auth.signup.perpose.SignUpPurposeFragment
import com.stephenleedev.neighborandroid.ui.auth.signup.profile.SignUpProfileFragment
import com.stephenleedev.neighborandroid.util.viewpager.PagerFragmentStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    private val viewPagerAdapter = PagerFragmentStateAdapter(this@SignUpActivity)

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
                    addFragment(SignUpApartmentFragment())
                    addFragment(SignUpPurposeFragment())
                    addFragment(SignUpProfileFragment())

                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)

                            // TODO : Create RegisterViewModel
//                            registerViewModel.setCurrentPage(page = position + 1)
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
                        viewPager.setCurrentItem(currentPage - 1, false)
                    }
                }
            }

            buttonLayout.buttonNext.apply {
                setOnClickListener {
                    val currentPage = viewPager.currentItem

                    // Go to the next page
                    if (currentPage < SIGNUP_TOTAL_FRAGMENT_COUNT) {
                        viewPager.setCurrentItem(currentPage + 1, false)
                    }

                    // Register user
                    else if (currentPage == SIGNUP_TOTAL_FRAGMENT_COUNT) {

                    }
                }
            }
        }
    }

    private fun initObservers() {

    }

    companion object {
        const val SIGNUP_TOTAL_FRAGMENT_COUNT = 3
    }

}