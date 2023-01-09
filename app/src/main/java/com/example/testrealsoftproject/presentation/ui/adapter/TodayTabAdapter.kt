package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.model.TabData
import com.example.testrealsoftproject.databinding.ItemTabBinding

@SuppressLint("NotifyDataSetChanged")
class TodayTabAdapter(private val context: Context) : RecyclerView.Adapter<TodayTabAdapter.VH>() {
    private var mList = mutableListOf<TabData>()
    private var selectedPosition = -1

    private var listener: ((Int) -> Unit)? = null

    fun setOnClickListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = ItemTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(inflate)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<TabData>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(position: Int) {
        mList.forEach {
            it.isSelected = false
        }
        mList[position].isSelected = true
        selectedPosition = position
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemTabBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val data = mList[adapterPosition]

                if (selectedPosition != -1) {
                    mList[selectedPosition].isSelected = false
                    notifyItemChanged(selectedPosition)
                }

                if (selectedPosition == adapterPosition) {
                    data.isSelected = false
                    selectedPosition = -1
                    notifyItemChanged(adapterPosition)
                } else {
                    data.isSelected = true
                    selectedPosition = adapterPosition
                    notifyItemChanged(selectedPosition)
                }

                listener?.invoke(adapterPosition)
            }
        }

        fun bind(data: TabData) {
            binding.apply {
                if (data.title != null) {
                    tvTitle.text = data.title

                    val color = if (data.isSelected) ContextCompat.getColor(
                        context, R.color.color_medium_slate_blue
                    ) else ContextCompat.getColor(context, R.color.color_grey_suit)

                    tvTitle.setTextColor(color)

                    if (data.count != null) tvCount.text = data.count.toString()
                    else tvCount.text = ""
                }
            }
        }
    }
}