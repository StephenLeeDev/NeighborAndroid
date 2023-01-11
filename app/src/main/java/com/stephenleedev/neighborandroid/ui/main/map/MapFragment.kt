package com.stephenleedev.neighborandroid.ui.main.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.stephenleedev.neighborandroid.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }

    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = false
    }

}