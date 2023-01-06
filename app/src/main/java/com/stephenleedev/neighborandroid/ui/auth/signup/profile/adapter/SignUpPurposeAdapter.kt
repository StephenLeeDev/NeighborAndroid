package com.stephenleedev.neighborandroid.ui.auth.signup.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.databinding.ItemSignUpPurposeBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickWithPositionListener
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

class SignUpPurposeAdapter(private val listener: ClickWithPositionListener<SignUpPurposeModel, Int>) : ListAdapter<SignUpPurposeModel, SignUpPurposeAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemSignUpPurposeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SignUpPurposeModel, position: Int) {
            binding.apply {
                this.model = model
                root.setOnClickListener { listener.onClick(model, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSignUpPurposeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].hashCode().toLong()
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<SignUpPurposeModel>() {
            override fun areItemsTheSame(oldItem: SignUpPurposeModel, newItem: SignUpPurposeModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SignUpPurposeModel, newItem: SignUpPurposeModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}