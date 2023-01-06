package com.stephenleedev.neighborandroid.ui.auth.signup.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.stephenleedev.neighborandroid.databinding.FragmentSignUpProfileBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickWithPositionListener
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeState
import com.stephenleedev.neighborandroid.ui.auth.signup.SignUpActivity
import com.stephenleedev.neighborandroid.ui.auth.signup.profile.adapter.SignUpPurposeAdapter
import com.stephenleedev.neighborandroid.util.extension.toDp
import com.stephenleedev.neighborandroid.util.logFunctions
import com.stephenleedev.neighborandroid.util.permission.PermissionUtil
import com.stephenleedev.neighborandroid.util.recyclerview.GridListDecoration
import com.stephenleedev.neighborandroid.viewmodel.auth.register.PurposeViewModel
import com.stephenleedev.neighborandroid.viewmodel.auth.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker

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
        initThumbnailView()
        initTextInput()
        initRecyclerView()
    }

    private fun initThumbnailView() {
        binding.thumbnailImageView.setOnClickListener {
            PermissionUtil().checkGrantStoragePermission(
                activity = requireActivity() as SignUpActivity,
                positiveListener = {
                    TedImagePicker.with(requireContext())
                        .start { uri ->
                            logFunctions("TedImagePicker : $uri")

                            registerViewModel.setThumbnailUri(uri)
                        }
                }
            )
        }
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
        initRegisterObserver()
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

    private fun initRegisterObserver() {
        registerViewModel.apply {

            thumbnailUri.observe(viewLifecycleOwner) { uri ->
                Glide
                    .with(requireContext())
                    .load(uri)
                    .transform(
                        MultiTransformation(
                            CenterCrop(), RoundedCorners(100.toDp)
                        )
                    )
                    .into(binding.thumbnailImageView)
            }

        }
    }

}