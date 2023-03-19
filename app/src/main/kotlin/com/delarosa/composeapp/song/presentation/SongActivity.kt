package com.delarosa.composeapp.song.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.delarosa.composeapp.song.domain.Song
import com.delarosa.composeapp.ui.theme.ComposeAppTheme

class SongActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                SongView(
                    song = intent.getParcelableExtra("song", Song::class.java)!!
                )
            }
        }
    }
}
