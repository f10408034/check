package com.vangood.chat

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.vangood.chat.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.hime3}")

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(offlineUri)
        binding.videoView.requestFocus()
        binding.videoView.start()
    }
}


