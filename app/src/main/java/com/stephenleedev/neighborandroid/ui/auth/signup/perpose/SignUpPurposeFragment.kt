package com.stephenleedev.neighborandroid.ui.auth.signup.perpose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpPurposeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpPurposeFragment : Fragment() {

    private val binding by lazy { FragmentSignUpPurposeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}