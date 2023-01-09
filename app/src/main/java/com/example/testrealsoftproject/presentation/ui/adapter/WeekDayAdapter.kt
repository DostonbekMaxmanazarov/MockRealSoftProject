package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.ItemChatMainBinding
import com.example.testrealsoftproject.databinding.ItemWeekDayBinding
import com.example.testrealsoftproject.model.ChatUsersData
import com.example.testrealsoftproject.model.WeekData

@SuppressLint("NotifyDataSetChanged")
class WeekDayAdapter : RecyclerView.Adapter<WeekDayAdapter.VH>() {
    private var mList = mutableListOf<WeekData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = ItemWeekDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(inflate)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<WeekData>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemWeekDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WeekData) {
            binding.apply {
                tvDayName.text = data.weekName
                tvDay.text = data.weekDay.toString()
            }
        }
    }
}