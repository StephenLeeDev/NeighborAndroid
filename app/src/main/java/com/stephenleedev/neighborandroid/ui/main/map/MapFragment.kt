package com.stephenleedev.neighborandroid.ui.main.map

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.stephenleedev.neighborandroid.databinding.FragmentMapBinding
import com.stephenleedev.neighborandroid.databinding.MapClusterBinding
import com.stephenleedev.neighborandroid.databinding.MapMarkerSelectedBinding
import com.stephenleedev.neighborandroid.databinding.MapMarkerUnselectedBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.request.get.RequestState
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.ui.main.map.adapter.RequestAdapter
import com.stephenleedev.neighborandroid.ui.request.detail.RequestDetailActivity
import com.stephenleedev.neighborandroid.util.extension.toDp
import com.stephenleedev.neighborandroid.util.logFunctions
import com.stephenleedev.neighborandroid.util.permission.PermissionUtil
import com.stephenleedev.neighborandroid.viewmodel.request.RequestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ted.gun0912.clustering.clustering.Cluster
import ted.gun0912.clustering.naver.TedNaverClustering

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }

    private val markerUnselectedBinding by lazy { MapMarkerUnselectedBinding.inflate(layoutInflater) }
    private val markerSelectedBinding by lazy { MapMarkerSelectedBinding.inflate(layoutInflater) }
    private val clusterBinding by lazy { MapClusterBinding.inflate(layoutInflater) }

    private lateinit var requestAdapter: RequestAdapter

    private lateinit var naverMap: NaverMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestViewModel: RequestViewModel by viewModels()

    private val clusterList = mutableMapOf<Cluster<RequestModel>, Marker>()
    private val markerList = mutableMapOf<RequestModel, Marker>()

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

        initViews()
        initObservers()
    }

    private fun initViews() {
        settingBottomSheet()
        settingRecyclerView()
    }

    private fun settingBottomSheet() {
        BottomSheetBehavior.from(binding.bottomSheet.root).apply {
            isFitToContents = false
            halfExpandedRatio = 0.4f
        }
    }

    private fun settingRecyclerView() {
        requestAdapter = RequestAdapter(object : ClickListener<RequestModel> {
            override fun onClick(t: RequestModel) {

                // Open clicked request's detail screen
                requireActivity().startActivity(
                    Intent(requireActivity(), RequestDetailActivity::class.java).apply {
                        putExtra(RequestModel::class.java.simpleName, Gson().toJson(t))
                    }
                )
            }
        })

        binding.bottomSheet.recyclerView.adapter = requestAdapter
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = false

        moveToCurrentLocation()

        naverMap.addOnCameraIdleListener {
            naverMap.cameraPosition.target.apply {
                getRequestListFromCurrentLocation(
                    latitude = latitude,
                    longitude = longitude
                )
            }
        }

        /**
         * An issue that I can't grasp!!!
         *
         * When I update clustering through the moveToCurrentLocation() and updateMarker() functions after the Naver map is first loaded, it is not reflected in the map UI
         * It has been confirmed that it is not a problem such as an initialization problem of Naver map objects or network communication delay in network communication.
         * As a result of checking with logs and debuggers, the Naver map object is clearly initialized.
         * And also, the data from the server is being correctly delivered to TedNaverClustering after the completion of the communication.
         *
         * As a alternative option, reset the camera position 1 second after the Naver map loaded (only once in the first run)
         * After giving a 1-second delay as shown below, call the surrounding data again through the addOnCameraIdleListener to ensure that the clustering UI is usually updated.
         */

        /**
         * 파악이 안되는 이슈!!!
         *
         * 처음 네이버 지도가 로딩된 후 moveToCurrentLocation() 및 updateMarker() 함수를 통해 클러스터링을 업데이트하면, 지도 UI에 반영이 안됨
         * 네이버 지도 객체의 초기화 문제나, 네트워크 통신 딜레이 같은 문제는 아닌 것으로 확인됨
         * 로그 및 디버거로 확인한 결과 분명 네이버 지도 객체도 초기화 되너 있고,
         * 통신 완료 후 주변 모임 데이터를 제대로 TedNaverClustering에 전달하고 있음
         *
         * 차선책으로 네이버 지도 로딩 1초 후에 카메라 위치 세팅을 하여 대응 (최초 실행 시 1번만)
         * 아래 처럼 1초 딜레이를 주고나서 상기 addOnCameraIdleListener를 통해 주변 모임을 다시 호출하여 정상적으로 클러스터링 UI 갱신되도록 조치
         */

        lifecycleScope.launch {
            delay(2000)
            moveToCurrentLocation()
        }
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
                                CameraUpdate.scrollTo(
                                    LatLng(location.latitude, location.longitude)
                                )
                            )
                        }
                    }
            }
        )
    }

    private fun getRequestListFromCurrentLocation(latitude: Double, longitude: Double) {
        requestViewModel.getRequestListFromCurrentLocation(
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun initObservers() {
        initRequestObservers()
    }

    private fun initRequestObservers() {
        requestViewModel.apply {

            // Origin request list
            requestState.observe(viewLifecycleOwner) { state ->
                if (state is RequestState.Success) {
                    updateMarker(state.list)
                    setSelectedRequestList(state.list)

                    logFunctions("RequestList : ${state.list}")
                }
            }

            // Selected request list
            selectedRequestList.observe(viewLifecycleOwner) { list ->
                requestAdapter.submitList(list)
                binding.bottomSheet.emptyTextView.isVisible = list.isEmpty()
            }
        }
    }

    private fun updateMarker(groups: List<RequestModel>) {

        if (!::naverMap.isInitialized) return

        TedNaverClustering.with<RequestModel>(
            requireActivity(),
            naverMap
        )
            .customMarker {
                Marker().apply {
                    icon = OverlayImage.fromView(markerUnselectedBinding.root)
                    width = 34.toDp
                    height = 34.toDp
                }
            }
            .markerAddedListener { clusterItem, tedNaverMarker ->
                this.markerList[clusterItem] = tedNaverMarker.marker
            }
            .markerClickListener {
                clearMarkers()

                requestViewModel.setSelectedRequestList(listOf(it))

                this.markerList[it]?.apply {
                    icon = OverlayImage.fromView(
                        markerSelectedBinding.apply {
                            markerSelected.text = "1"
                        }.root
                    )
                    width = 34.toDp
                    height = 34.toDp
                }
            }
            .customCluster {
                clusterBinding.cluster.apply {
                    text = it.size.toString()
                    width = 34.toDp
                    height = 34.toDp
                }
            }
            .clusterAddedListener { cluster, tedNaverMarker ->
                clusterList[cluster] = tedNaverMarker.marker
            }
            .clusterClickListener {
                clearMarkers()

                requestViewModel.setSelectedRequestList(it.items.toMutableList())

                logFunctions("clusterClickListener : ${it.items}")

                clusterList[it]?.apply {
                    icon = OverlayImage.fromView(
                        markerSelectedBinding.apply {
                            markerSelected.text = it.size.toString()
                        }.root
                    )
                    width = 34.toDp
                    height = 34.toDp
                }
            }
            .minClusterSize(1)
            .clickToCenter(false)
            .items(groups)
            .make()

        logFunctions("updateMarker : $groups")
    }

    private fun clearMarkers() {
        markerList.map { item ->
            item.value.apply {
                icon = OverlayImage.fromView(markerUnselectedBinding.root)
                width = 34.toDp
                height = 34.toDp
            }
        }
        clusterList.map { item ->
            item.value.apply {
                icon = OverlayImage.fromView(
                    clusterBinding.apply {
                        cluster.text = item.key.size.toString()
                    }.root
                )
                width = 34.toDp
                height = 34.toDp
            }
        }
    }


    /********************************************************************************************/

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