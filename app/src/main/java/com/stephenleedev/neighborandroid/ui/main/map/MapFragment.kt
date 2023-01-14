package com.stephenleedev.neighborandroid.ui.main.map

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.stephenleedev.neighborandroid.databinding.FragmentMapBinding
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.util.permission.PermissionUtil

class MapFragment : Fragment(), OnMapReadyCallback {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }

    private lateinit var naverMap: NaverMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = false

        moveToCurrentLocation()
    }

    private fun moveToCurrentLocation() {
        PermissionUtil().checkGrantLocationPermission(
            activity = requireActivity() as MainActivity,
            positiveListener = {

                // Get current location
                fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(requireActivity())
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {

                            moveCamera(
                                CameraUpdate.scrollTo(LatLng(location.latitude, location.longitude))
                            )
                        }
                    }
            }
        )
    }

    private fun moveCamera(cameraUpdate: CameraUpdate) {
        naverMap.moveCamera(cameraUpdate)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

}