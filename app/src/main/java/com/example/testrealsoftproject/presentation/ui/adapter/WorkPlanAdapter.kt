package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.ItemNewTaskBinding
import com.example.testrealsoftproject.model.WorkPlanData

@SuppressLint("NotifyDataSetChanged")
class WorkPlanAdapter : RecyclerView.Adapter<WorkPlanAdapter.VH>() {
    private var mList = mutableListOf<WorkPlanData>()

    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = ItemNewTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(inflate, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<WorkPlanData>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemNewTaskBinding, context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        private var mediaPlayer: MediaPlayer? = null
        private var isStartOrPause = false

        init {
            mediaPlayer = MediaPlayer.create(context, R.raw.music)

            binding.waveSeekbar.setSampleFrom(R.raw.music)

            binding.ivPlay.setOnClickListener {
                if (isStartOrPause) {
                    mediaPlayer?.pause()
                    isStartOrPause = false
                    binding.ivPlay.setImageResource(R.drawable.ic_play_circle)
                } else {
                    mediaPlayer?.start()
                    isStartOrPause = true
                    binding.ivPlay.setImageResource(R.drawable.ic_pause_circle)
                }
            }

            binding.ivCheck.setOnClickListener {
                val data = mList[adapterPosition]

                if (selectedPosition == adapterPosition) {
                    data.isSelected = false
                    selectedPosition = -1
                    notifyItemChanged(adapterPosition)
                } else {
                    data.isSelected = true
                    selectedPosition = adapterPosition
                    notifyItemChanged(selectedPosition)
                }
            }
        }

        fun bind(data: WorkPlanData) {
            binding.apply {
                if (data.voice) {
                    ivPlay.visibility = View.VISIBLE
                    waveSeekbar.visibility = View.VISIBLE
                    tvText.visibility = View.GONE
                } else {
                    ivPlay.visibility = View.GONE
                    waveSeekbar.visibility = View.GONE
                    tvText.visibility = View.VISIBLE
                    tvText.text = data.message
                }

                if (data.isSelected) {
                    ivCheck.setBackgroundResource(R.drawable.ic_success_green)
                    ivCheck.setImageResource(R.drawable.ic_check_white)
                } else {
                    ivCheck.setBackgroundResource(R.drawable.ic_success_white)
                    ivCheck.setImageResource(R.drawable.ic_check_grey)
                }
            }
        }
    }
}