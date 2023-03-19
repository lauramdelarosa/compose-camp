package com.delarosa.composeapp.song.presentation

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.delarosa.composeapp.R
import com.delarosa.composeapp.song.data.MainRepository
import com.delarosa.composeapp.song.domain.Song
import com.delarosa.composeapp.ui.theme.GrayBlack
import com.delarosa.composeapp.ui.theme.textBodyLargeStyle
import com.delarosa.composeapp.ui.theme.textColorContentStyle
import com.delarosa.composeapp.ui.theme.textColorTitleStyle

@Preview()
@Composable
fun SongView(
    modifier: Modifier = Modifier,
    song: Song,
    viewModel: SongViewModel = SongViewModel(MainRepository()),
) {
    val songStateFlow by viewModel.getSongListFlow.collectAsState()

    Column(
        modifier = modifier
            .background(brush = Brush.verticalGradient(colors = getGradientListColor(song.image)))
    ) {
        PlaylistContent(song = song)
        LazyColumn {
            items(songStateFlow) { song ->
                itemList(song = song)
            }
        }
    }
}

@Composable
fun PlaylistContent(
    modifier: Modifier = Modifier,
    song: Song,
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = song.image),
            contentDescription = "spotify",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "¡Disfruta con los grandes del rock colombiano! Foto: Diamante Electrico",
            modifier = Modifier.padding(bottom = 7.dp, start = 8.dp, end = 8.dp),
            color = textColorContentStyle()
        )
        Row(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.spotify),
                contentDescription = "spotify",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Spotify",
                color = textColorTitleStyle(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 7.dp)
            )
        }
        Text(
            text = "82.141 veces guardada ~ 3 h 37m",
            color = textColorContentStyle(),
            modifier = Modifier.padding(bottom = 7.dp, start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
fun itemList(
    modifier: Modifier = Modifier, song: Song
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = song.image),
            contentDescription = song.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(70.dp)
                .padding(bottom = 7.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = song.name,
                color = textColorTitleStyle(),
                style = textBodyLargeStyle(),
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = song.artist,
                color = textColorContentStyle(),

                )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .padding(end = 20.dp)
                .align(Alignment.CenterVertically)
                .rotate(90f),
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Person Icon",
            tint = Color.Gray
        )
    }
}

@Composable
fun getGradientListColor(image: Int): List<Color> {
    val bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, image)
    val mainColor = remember {
        Palette.from(bitmap).generate().darkVibrantSwatch
    }
    val secondColor = remember {
        Palette.from(bitmap).generate().darkMutedSwatch
    }
    return listOf(
        mainColor?.let { Color(it.rgb) } ?: secondColor?.let { Color(it.rgb) } ?: Color.Black,
        GrayBlack,
        Color.Black)
}

