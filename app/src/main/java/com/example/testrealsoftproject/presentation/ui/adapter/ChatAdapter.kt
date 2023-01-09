package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.*
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.model.BaseChatData

private const val TYPE_LEFT_PHOTO = R.layout.item_image_left
private const val TYPE_LEFT_MESSAGE = R.layout.item_message_left
private const val TYPE_LEFT_FILE = R.layout.item_file_left
private const val TYPE_RIGHT_MESSAGE = R.layout.item_message_right
private const val TYPE_RIGHT_VOICE = R.layout.item_voice_right
private const val TYPE_DAY = R.layout.item_day

@SuppressLint("NotifyDataSetChanged")
class ChatAdapter : RecyclerView.Adapter<BaseChatVH>() {

    private val mList = mutableListOf<BaseChatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            TYPE_RIGHT_MESSAGE -> BaseChatVH.RightMessageVH(ItemMessageRightBinding.bind(view))
            TYPE_LEFT_MESSAGE -> BaseChatVH.LeftMessageVH(ItemMessageLeftBinding.bind(view))
            TYPE_RIGHT_VOICE -> BaseChatVH.RightVoiceVH(
                ItemVoiceRightBinding.bind(view), parent.context
            )
            TYPE_LEFT_PHOTO -> BaseChatVH.LeftPhotoVH(ItemImageLeftBinding.bind(view))
            TYPE_LEFT_FILE -> BaseChatVH.LeftFileVH(ItemFileLeftBinding.bind(view))
            TYPE_DAY -> BaseChatVH.DayVH(ItemDayBinding.bind(view))
            else -> throw java.lang.IllegalArgumentException("wrong item type")
        }
    }

    override fun onBindViewHolder(holder: BaseChatVH, position: Int) {
        when (holder) {
            is BaseChatVH.RightMessageVH -> holder.bind(mList[position] as BaseChatData.RightMessageData)
            is BaseChatVH.LeftMessageVH -> holder.bind(mList[position] as BaseChatData.LeftMessageData)
            is BaseChatVH.RightVoiceVH -> holder.bind(mList[position] as BaseChatData.RightVoiceData)
            is BaseChatVH.LeftPhotoVH -> holder.bind(mList[position] as BaseChatData.LeftPhotoData)
            is BaseChatVH.LeftFileVH -> holder.bind(mList[position] as BaseChatData.LeftFileData)
            is BaseChatVH.DayVH -> holder.bind(mList[position] as BaseChatData.DayData)
        }
    }

    override fun getItemViewType(position: Int) = when (mList[position]) {
        is BaseChatData.RightMessageData -> TYPE_RIGHT_MESSAGE
        is BaseChatData.LeftMessageData -> TYPE_LEFT_MESSAGE
        is BaseChatData.RightVoiceData -> TYPE_RIGHT_VOICE
        is BaseChatData.LeftPhotoData -> TYPE_LEFT_PHOTO
        is BaseChatData.LeftFileData -> TYPE_LEFT_FILE
        is BaseChatData.DayData -> TYPE_DAY
        else -> throw java.lang.IllegalArgumentException("wrong item type")
    }

    override fun getItemCount() = mList.size

    fun submitList(data: List<BaseChatData>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

}