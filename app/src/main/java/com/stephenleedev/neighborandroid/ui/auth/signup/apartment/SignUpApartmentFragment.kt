package com.stephenleedev.neighborandroid.ui.auth.signup.apartment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpApartmentBinding
import com.stephenleedev.neighborandroid.util.logFunctions
import com.stephenleedev.neighborandroid.viewmodel.apartment.ApartmentViewModel

class SignUpApartmentFragment : Fragment() {

    private val binding by lazy { FragmentSignUpApartmentBinding.inflate(layoutInflater) }

    private val apartmentViewModel: ApartmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        apartmentViewModel.apply {
            getAllApartmentList()
            apartmentState.observe(viewLifecycleOwner) { list ->
                logFunctions("apartmentState : $list")
            }
        }
    }

}