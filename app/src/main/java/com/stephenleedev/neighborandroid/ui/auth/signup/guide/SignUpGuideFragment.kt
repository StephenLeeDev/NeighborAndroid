package com.stephenleedev.neighborandroid.ui.auth.signup.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpGuideBinding

class SignUpGuideFragment : Fragment() {

    private val binding by lazy { FragmentSignUpGuideBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}