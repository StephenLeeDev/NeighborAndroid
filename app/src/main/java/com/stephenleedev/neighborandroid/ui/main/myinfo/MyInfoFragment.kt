package com.stephenleedev.neighborandroid.ui.main.myinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.FragmentMyInfoBinding

class MyInfoFragment : Fragment() {

    private val binding by lazy { FragmentMyInfoBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}