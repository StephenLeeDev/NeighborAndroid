package com.stephenleedev.neighborandroid.ui.main.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stephenleedev.neighborandroid.databinding.ItemChatRoomBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplicationModel

/**
 * Written by StephenLeeDev on 2023/01/31.
 */

class ChatRoomListAdapter(private val listener: ClickListener<RequestApplicationModel>) : ListAdapter<RequestApplicationModel, ChatRoomListAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemChatRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RequestApplicationModel) {
            binding.apply {
                this.model = model
                root.setOnClickListener { listener.onClick(model) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<RequestApplicationModel>() {
            override fun areItemsTheSame(oldItem: RequestApplicationModel, newItem: RequestApplicationModel): Boolean {
                return oldItem.request.id == newItem.request.id
            }

            override fun areContentsTheSame(oldItem: RequestApplicationModel, newItem: RequestApplicationModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}