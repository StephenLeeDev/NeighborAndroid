package com.stephenleedev.neighborandroid.ui.auth.signup.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpProfileBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickWithPositionListener
import com.stephenleedev.neighborandroid.domain.model.auth.register.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.model.auth.register.SignUpPurposeState
import com.stephenleedev.neighborandroid.ui.auth.signup.profile.adapter.SignUpPurposeAdapter
import com.stephenleedev.neighborandroid.util.recyclerview.GridListDecoration
import com.stephenleedev.neighborandroid.viewmodel.auth.register.PurposeViewModel
import com.stephenleedev.neighborandroid.viewmodel.auth.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpProfileFragment : Fragment() {

    private val binding by lazy { FragmentSignUpProfileBinding.inflate(layoutInflater) }

    private val registerViewModel: RegisterViewModel by activityViewModels()
    private val purposeViewModel: PurposeViewModel by activityViewModels()

    private lateinit var signUpPurposeAdapter: SignUpPurposeAdapter

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
        initTextInput()
        initRecyclerView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTextInput() {
        binding.textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(a: CharSequence, q: Int, w: Int, e: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {}
            override fun onTextChanged(s: CharSequence, d: Int, f: Int, b: Int) {
                registerViewModel.setNickname(s.toString())
            }
        })
    }

    private fun initRecyclerView() {
        signUpPurposeAdapter = SignUpPurposeAdapter(object : ClickWithPositionListener<SignUpPurposeModel, Int> {
            override fun onClick(t: SignUpPurposeModel, position: Int) {
                t.isSelected = !t.isSelected
                signUpPurposeAdapter.notifyItemChanged(position)

                registerViewModel.setSelectedPurposeIdListByNewState(signUpPurposeAdapter.currentList)
            }
        })
        binding.purposeRecyclerView.apply {
            adapter = signUpPurposeAdapter
            addItemDecoration(GridListDecoration(spanCount = 3))
        }
    }

    private fun initObservers() {
        initPurposeListObservers()
    }

    private fun initPurposeListObservers() {
        purposeViewModel.apply {
            getSignUpPurposeList()

            signUpPurposeState.observe(viewLifecycleOwner) { purposeState ->
                if (purposeState is SignUpPurposeState.Success) {
                    signUpPurposeAdapter.submitList(purposeState.list)
                }
            }
        }
    }

}