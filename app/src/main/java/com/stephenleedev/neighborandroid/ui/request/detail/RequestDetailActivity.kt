package com.stephenleedev.neighborandroid.ui.request.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.ActivityRequestDetailBinding
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplyResponse
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplyState
import com.stephenleedev.neighborandroid.ui.main.MainActivity.Companion.APPLY_TO_REQUEST_SUCCESSFULLY
import com.stephenleedev.neighborandroid.viewmodel.request.RequestApplyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding by lazy { ActivityRequestDetailBinding.inflate(layoutInflater).apply {
            model = requestModel
            requestInfoLayout.model = requestModel
        }
    }

    private val requestModel by lazy {
        Gson().fromJson(
            intent.getStringExtra(RequestModel::class.java.simpleName),
            RequestModel::class.java
        )
    }

    private val requestApplyViewModel : RequestApplyViewModel by viewModels()

    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        initViews()
        initObservers()
    }

    private fun initViews() {
        initBottomButtonLayout()
    }

    private fun initBottomButtonLayout() {

        binding.requestInfoLayout.apply {
            titleTextView.maxLines = Integer.MAX_VALUE
            messageTextView.maxLines = Integer.MAX_VALUE
        }

        binding.buttonLayout.apply {
            buttonPrevious.isVisible = false

            buttonNext.apply {
                text = getString(R.string.accepting_request)

                setOnClickListener {
                    requestApplyViewModel.apply {
                        if (requestApplyState.value is RequestApplyState.Ready) {
                            postApplyToRequest(requestModel.id)
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        requestApplyViewModel.requestApplyState.observe(this) { state ->

            when (state) {
                is RequestApplyState.Success -> {

                    sendBroadcast(
                        Intent(APPLY_TO_REQUEST_SUCCESSFULLY)
                            .putExtra(
                                RequestApplyResponse::class.java.simpleName,
                                Gson().toJson(state.requestApplyResponse)))

                    finish()
                }
                else -> {}
            }
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val location = requestModel.location
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(location.latitude, location.longitude))
        naverMap.moveCamera(cameraUpdate)

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = false
    }



    /********************************************************************************************/

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