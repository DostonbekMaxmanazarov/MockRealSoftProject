package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.ItemChatMainBinding
import com.example.testrealsoftproject.model.ChatUsersData

@SuppressLint("NotifyDataSetChanged")
class ChatMainAdapter : RecyclerView.Adapter<ChatMainAdapter.VH>() {
    private var mList = mutableListOf<ChatUsersData>()

    private var listener: ((ChatUsersData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate =
            ItemChatMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(inflate)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<ChatUsersData>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemChatMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: ChatUsersData) {
            binding.apply {
                ivPersonPhoto.setImageResource(loadImages().random())
                tvName.text = data.name
                tvMessage.text = data.message
                tvTime.text = data.time

                if (data.messageCount != null) tvMessageCount.text = data.messageCount.toString()
                else tvMessageCount.visibility = View.GONE

                if (data.isYou) {
                    tvYou.visibility = View.VISIBLE
                    tvYou.setText(R.string.you)
                } else tvYou.visibility = View.GONE

                if (data.isYouSend) ivCheck.visibility = View.VISIBLE
                else ivCheck.visibility = View.GONE
            }
        }

        private fun loadImages(): MutableList<Int> {
            val images = mutableListOf<Int>()
            images.add(R.drawable.im_person_5)
            images.add(R.drawable.im_person_6)
            images.add(R.drawable.im_person_7)
            images.add(R.drawable.im_person_8)

            return images
        }
    }

    fun setOnClickListener(listener: (ChatUsersData) -> Unit) {
        this.listener = listener
    }
}