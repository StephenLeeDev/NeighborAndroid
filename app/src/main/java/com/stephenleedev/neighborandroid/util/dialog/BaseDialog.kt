package com.stephenleedev.neighborandroid.util.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.FragmentBaseDialogBinding

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

class BaseDialog(
    private val content: String,
    private val buttonPositiveContent: String? = null,
    private val buttonCancelContent: String? = null,
    private val onPositiveClick: () -> Unit = {},
    private val onNegativeClick: () -> Unit = {},
) : DialogFragment() {

    private val binding by lazy { FragmentBaseDialogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.Theme_NeighborAndroid_DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.apply {
            titleTextView.text = content
            positiveButton.apply {
                text = buttonPositiveContent
                setOnClickListener {
                    onPositiveClick()
                    dismiss()
                }
            }
            negativeButton.apply {
                isVisible = !buttonCancelContent.isNullOrEmpty()

                text = buttonCancelContent
                setOnClickListener {
                    onNegativeClick()
                    dismiss()
                }
            }
        }

        return binding.root
    }

}