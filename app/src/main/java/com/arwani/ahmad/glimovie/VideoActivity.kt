package com.arwani.ahmad.glimovie

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arwani.ahmad.glimovie.data.network.response.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoData = intent.getParcelableExtra<Video>(EXTRA_KEY)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = videoData?.name


        val thirdPartyYouTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.third_party_player_view)

        thirdPartyYouTubePlayerView.enableAutomaticInitialization = false

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultPlayerUiController =
                    DefaultPlayerUiController(thirdPartyYouTubePlayerView, youTubePlayer)
                defaultPlayerUiController.showFullscreenButton(true)
           defaultPlayerUiController.setFullScreenButtonClickListener {
                    if (thirdPartyYouTubePlayerView.isFullScreen()) {
                        thirdPartyYouTubePlayerView.exitFullScreen()
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                        if (supportActionBar != null) {
                            supportActionBar!!.show()
                        }
                    } else {
                        thirdPartyYouTubePlayerView.enterFullScreen()
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                        if (supportActionBar != null) {
                            supportActionBar!!.hide()
                        }
                    }
                }


                thirdPartyYouTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                val videoId = videoData?.key
                if (videoId != null) {
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            }
        }

        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        thirdPartyYouTubePlayerView.initialize(listener, options)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_KEY = "video_key"
    }
}