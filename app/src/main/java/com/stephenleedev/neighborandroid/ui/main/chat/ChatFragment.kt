package com.stephenleedev.neighborandroid.ui.main.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stephenleedev.neighborandroid.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}