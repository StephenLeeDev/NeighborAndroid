package com.stephenleedev.neighborandroid.ui.main.chat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.stephenleedev.neighborandroid.databinding.FragmentChatBinding
import com.stephenleedev.neighborandroid.domain.`interface`.ClickListener
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplicationModel
import com.stephenleedev.neighborandroid.ui.main.MainActivity
import com.stephenleedev.neighborandroid.ui.main.chat.adapter.ChatRoomListAdapter
import com.stephenleedev.neighborandroid.viewmodel.chat.ChatRoomViewModel

class ChatFragment : Fragment() {

    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }

    private val chatRoomViewModel: ChatRoomViewModel by activityViewModels()

    private lateinit var chatRoomListAdapter: ChatRoomListAdapter

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {

                MainActivity.APPLY_TO_REQUEST_SUCCESSFULLY -> {

                    val requestApplicationModel: RequestApplicationModel =
                        Gson().fromJson(
                            intent.getStringExtra(RequestApplicationModel::class.java.simpleName),
                            RequestApplicationModel::class.java
                        )

                    chatRoomViewModel.addChatRoomToList(newItem = requestApplicationModel)
                }

            }
        }
    }

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
        initBroadcastReceiver()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        chatRoomListAdapter = ChatRoomListAdapter(object : ClickListener<RequestApplicationModel> {
            override fun onClick(t: RequestApplicationModel) {
                TODO("Not yet implemented")
            }
        })

        binding.recyclerView.adapter = chatRoomListAdapter
    }

    private fun initObservers() {
        chatRoomViewModel.chatRoomList.observe(viewLifecycleOwner) { rooms ->

            chatRoomListAdapter.submitList(rooms)

            binding.emptyTextView.isVisible = rooms.isNullOrEmpty()
        }
    }

    private fun initBroadcastReceiver() {
        val intentFilter = IntentFilter().apply {
            addAction(MainActivity.APPLY_TO_REQUEST_SUCCESSFULLY)
        }
        requireActivity().registerReceiver(broadcastReceiver, intentFilter)
    }

}