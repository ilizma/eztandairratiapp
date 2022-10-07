package com.ilizma.app.view.activity

import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilizma.app.view.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        //if (BuildConfig.DEBUG) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        // As a music player, the volume controls should adjust the music volume while in the app.
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

}