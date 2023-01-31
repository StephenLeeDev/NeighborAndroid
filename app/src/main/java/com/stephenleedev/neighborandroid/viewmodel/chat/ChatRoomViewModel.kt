package com.stephenleedev.neighborandroid.viewmodel.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplicationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2023/01/30.
 */

@HiltViewModel
class ChatRoomViewModel @Inject constructor() : ViewModel() {

    /**
     * Chat room list
     */
    private val _chatRoomList = MutableLiveData<List<RequestApplicationModel>>(emptyList())
    val chatRoomList = _chatRoomList as LiveData<List<RequestApplicationModel>>

    fun addChatRoomToList(newItem: RequestApplicationModel) {
        val originList = _chatRoomList.value?.toMutableList() ?: emptyList()
        _chatRoomList.value = listOf(newItem) + originList
    }

}