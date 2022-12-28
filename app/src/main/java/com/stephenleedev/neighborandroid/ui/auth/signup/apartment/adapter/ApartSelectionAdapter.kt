package com.stephenleedev.neighborandroid.ui.auth.signup.apartment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.databinding.ItemApartmentBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel

/**
 * Written by StephenLeeDev on 2022/12/28.
 */

class ApartSelectionAdapter(private val listener: ClickListener<ApartmentModel>) : ListAdapter<ApartmentModel, ApartSelectionAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemApartmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ApartmentModel) {
            binding.apply {
                this.model = model
                root.setOnClickListener { listener.onClick(model) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemApartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<ApartmentModel>() {
            override fun areItemsTheSame(oldItem: ApartmentModel, newItem: ApartmentModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ApartmentModel, newItem: ApartmentModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}