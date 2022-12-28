package com.stephenleedev.neighborandroid.ui.auth.signup.apartment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpApartmentBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentState
import com.stephenleedev.neighborandroid.ui.auth.signup.apartment.adapter.ApartSelectionAdapter
import com.stephenleedev.neighborandroid.util.logFunctions
import com.stephenleedev.neighborandroid.util.recyclerview.VerticalListDecoration
import com.stephenleedev.neighborandroid.viewmodel.apartment.ApartmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpApartmentFragment : Fragment() {

    private val binding by lazy { FragmentSignUpApartmentBinding.inflate(layoutInflater) }

    private val apartmentViewModel: ApartmentViewModel by activityViewModels()

    private lateinit var apartmentSelectionAdapter: ApartSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        apartmentSelectionAdapter = ApartSelectionAdapter(object : ClickListener<ApartmentModel> {
            override fun onClick(t: ApartmentModel) {
                val copyList = apartmentSelectionAdapter.currentList.toMutableList()

                copyList.forEach { apart ->
                    apart.isSelected = apart.id == t.id
                }

                apartmentViewModel.apply {
                    // Update new apartment list state
                    setApartmentState(ApartmentState.Success(copyList))

                    // Update new selected apartment id state
                    setSelectedApartmentId(copyList.find { it.isSelected }?.id ?: -1)
                }
            }
        })
        binding.apartmentRecyclerView.apply {
            adapter = apartmentSelectionAdapter
            addItemDecoration(VerticalListDecoration())
        }
    }

    private fun initObservers() {
        apartmentViewModel.apply {
            getAllApartmentList()
            apartmentState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ApartmentState.Success -> {
                        val copyList = state.list.toMutableList()
                        apartmentSelectionAdapter.submitList(copyList)
                        logFunctions("apartmentList : ${copyList}")

                        apartmentSelectionAdapter.notifyDataSetChanged()
                    }
                    else -> {}
                }
            }
        }
    }

}