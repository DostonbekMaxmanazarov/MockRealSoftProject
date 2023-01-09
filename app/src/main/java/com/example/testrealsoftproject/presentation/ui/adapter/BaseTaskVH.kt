package com.example.testrealsoftproject.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testrealsoftproject.R
import com.example.testrealsoftproject.databinding.ItemDayBinding
import com.example.testrealsoftproject.databinding.ItemFileLeftBinding
import com.example.testrealsoftproject.databinding.ItemImageLeftBinding
import com.example.testrealsoftproject.databinding.ItemMessageLeftBinding
import com.example.testrealsoftproject.databinding.ItemMessageRightBinding
import com.example.testrealsoftproject.databinding.ItemVoiceRightBinding
import com.example.testrealsoftproject.model.BaseChatData
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar

sealed class BaseTaskVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class LeftPhotoVH(private val binding: ItemImageLeftBinding) : BaseTaskVH(binding.root) {
        fun bind(photoData: BaseChatData.LeftPhotoData) {
            binding.ivPhoto.setImageResource(R.drawable.im_photo_home)
            binding.tvTime.setText(R.string.time)
        }
    }

    class LeftFileVH(private val binding: ItemFileLeftBinding) : BaseTaskVH(binding.root) {
        fun bind(fileData: BaseChatData.LeftFileData) {
            binding.ivFile.setImageResource(R.drawable.ic_file)
            binding.tvTime.setText(R.string.time)
            binding.tvTitle.setText(R.string.file_text)
        }
    }

    class LeftMessageVH(private val binding: ItemMessageLeftBinding) : BaseTaskVH(binding.root) {
        fun bind(messageData: BaseChatData.LeftMessageData) {
            binding.tvMessageLeft.setText(R.string.message)
        }
    }

    class RightMessageVH(private val binding: ItemMessageRightBinding) : BaseTaskVH(binding.root) {
        fun bind(messageData: BaseChatData.RightMessageData) {
            binding.tvMessage.setText(R.string.message)
        }
    }

    @SuppressLint("SetTextI18n")
    class RightVoiceVH(private val binding: ItemVoiceRightBinding, context: Context) :
        BaseTaskVH(binding.root) {
        private var mediaPlayer: MediaPlayer? = null
        private var isStartOrPause = false

        init {
            mediaPlayer = MediaPlayer.create(context, R.raw.music)

            val millSec = mediaPlayer?.duration
            val sec = millSec?.div(1000)
            val min = sec?.div(60)
            val residualSec = sec?.rem(60)

            if (min != null) {
                if (min < 10) {
                    binding.tvAudioTime.text = "0$min:$residualSec"
                } else binding.tvAudioTime.text = "$min:$residualSec"
            }

            binding.waveSeekbar.setSampleFrom(R.raw.music)

            binding.root.setOnClickListener {
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
        }

        fun bind(messageData: BaseChatData.RightVoiceData) {

        }
    }

    class DayVH(private val binding: ItemDayBinding) : BaseTaskVH(binding.root) {
        fun bind(messageData: BaseChatData.DayData) {
            binding.tvDay.setText(R.string.today)
        }
    }
}