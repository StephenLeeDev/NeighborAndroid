package com.stephenleedev.neighborandroid.ui.main.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.databinding.ItemRequestBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel

/**
 * Written by StephenLeeDev on 2023/01/26.
 */

class RequestAdapter(private val listener: ClickListener<RequestModel>) : ListAdapter<RequestModel, RequestAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RequestModel) {
            binding.apply {
                this.model = model
                root.setOnClickListener { listener.onClick(model) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<RequestModel>() {
            override fun areItemsTheSame(oldItem: RequestModel, newItem: RequestModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RequestModel, newItem: RequestModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}