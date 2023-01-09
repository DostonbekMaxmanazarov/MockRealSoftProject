package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.datasource.network.response.TaskItemsResponse
import com.example.testrealsoftproject.databinding.ItemTodayBinding

@SuppressLint("NotifyDataSetChanged")
class TodayTasksAdapter : RecyclerView.Adapter<TodayTasksAdapter.VH>() {
    private var mList = mutableListOf<TaskItemsResponse>()

    private var listener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = ItemTodayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(inflate)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<TaskItemsResponse>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemTodayBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener?.invoke(adapterPosition)
            }
        }

        fun bind(data: TaskItemsResponse) {
            binding.apply {
                ivPersonPhoto.setImageResource(loadImages())
                tvFullName.text = data.name
                tvNumberCode.text = data.numberCode
                tvDateTop.text = data.date
                tvDateBottom.text = data.date
                tvDescription.text = data.description
            }
        }

        private fun loadImages(): Int {
            val images = mutableListOf<Int>()
            images.add(R.drawable.im_person)
            images.add(R.drawable.im_person_2)
            images.add(R.drawable.im_person_3)
            images.add(R.drawable.im_person_4)

            return images.random()
        }
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        this.listener = listener
    }
}