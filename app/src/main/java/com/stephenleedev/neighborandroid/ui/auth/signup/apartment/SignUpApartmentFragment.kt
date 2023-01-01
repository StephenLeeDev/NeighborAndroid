package com.stephenleedev.neighborandroid.ui.auth.signup.apartment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpApartmentBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickWithPositionListener
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentState
import com.stephenleedev.neighborandroid.ui.auth.signup.apartment.adapter.ApartSelectionAdapter
import com.stephenleedev.neighborandroid.util.recyclerview.VerticalListDecoration
import com.stephenleedev.neighborandroid.viewmodel.auth.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpApartmentFragment : Fragment() {

    private val binding by lazy { FragmentSignUpApartmentBinding.inflate(layoutInflater) }

    private val registerViewModel: RegisterViewModel by activityViewModels()

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
        apartmentSelectionAdapter = ApartSelectionAdapter(object : ClickWithPositionListener<ApartmentModel, Int> {
            override fun onClick(t: ApartmentModel, position: Int) {
                apartmentSelectionAdapter.currentList.forEach { apart ->
                    apart.isSelected = apart.id == t.id
                }

                registerViewModel.apply {
                    // Update new apartment list state
                    setApartmentState(ApartmentState.Success(apartmentSelectionAdapter.currentList))

                    // Update new selected apartment id state
                    setSelectedApartmentId(apartmentSelectionAdapter.currentList[position].id)
                }
            }
        })
        binding.apartmentRecyclerView.apply {
            adapter = apartmentSelectionAdapter
            addItemDecoration(VerticalListDecoration())
        }
    }

    private fun initObservers() {
        registerViewModel.apply {
            getAllApartmentList()
            apartmentState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ApartmentState.Success -> {
                        val copyList = state.list.toMutableList()
                        apartmentSelectionAdapter.submitList(copyList)

                        apartmentSelectionAdapter.notifyDataSetChanged()
                    }
                    else -> {}
                }
            }
        }
    }

}