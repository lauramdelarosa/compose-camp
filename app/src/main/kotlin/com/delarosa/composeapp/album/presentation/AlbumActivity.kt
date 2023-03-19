package com.delarosa.composeapp.album.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.delarosa.composeapp.ui.theme.ComposeAppTheme

class AlbumActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                AlbumView()
            }
        }
    }
}
