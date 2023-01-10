package com.stephenleedev.neighborandroid.ui.main.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}