package com.delarosa.composeapp.album.presentation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delarosa.composeapp.album.data.AlbumRepository
import com.delarosa.composeapp.album.domain.Album
import com.delarosa.composeapp.song.presentation.SongActivity
import com.delarosa.composeapp.ui.theme.textBodyLargeStyle
import com.delarosa.composeapp.ui.theme.textColorContentStyle
import com.delarosa.composeapp.ui.theme.textColorTitleStyle


@Preview
@Composable
fun AlbumView(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = AlbumViewModel(AlbumRepository()),
) {
    val albumListStateFlow by viewModel.getAlbumListFlow.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(Color.Black)
            .padding(top = 20.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(albumListStateFlow) { album ->
                itemList(album = album, getDetail = {
                    context.startActivity(Intent(context, SongActivity::class.java).apply {
                        putExtra("song", album.song)
                    })
                })
            }
        }
    }
}


@Composable
fun itemList(
    modifier: Modifier = Modifier,
    album: Album,
    getDetail: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                getDetail()
            },
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = album.image),
            contentDescription = album.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 7.dp)
        )
        Text(
            text = album.name,
            color = textColorTitleStyle(),
            style = textBodyLargeStyle()
        )
        Text(
            text = album.artist,
            color = textColorContentStyle(),
        )
        Spacer(modifier = modifier.size(40.dp))
    }
}
