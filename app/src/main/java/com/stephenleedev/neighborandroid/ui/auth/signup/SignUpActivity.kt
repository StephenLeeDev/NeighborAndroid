package com.stephenleedev.neighborandroid.ui.auth.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.stephenleedev.neighborandroid.databinding.ActivitySignUpBinding
import com.stephenleedev.neighborandroid.ui.auth.signup.apartment.SignUpApartmentFragment
import com.stephenleedev.neighborandroid.ui.auth.signup.guide.SignUpGuideFragment
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
                    val currentPage = viewPager.currentItem

                    // Go to the next page
                    if (currentPage < SIGNUP_TOTAL_FRAGMENT_COUNT) {
                        viewPager.setCurrentItem(currentPage + 1, true)
                    }

                    // Register user
                    else if (currentPage == SIGNUP_TOTAL_FRAGMENT_COUNT) {
                        // TODO : Register user POST API
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

        }
    }

    companion object {
        const val SIGNUP_TOTAL_FRAGMENT_COUNT = 3
    }

}