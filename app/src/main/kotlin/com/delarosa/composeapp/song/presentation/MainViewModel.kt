package com.delarosa.composeapp.song.presentation

import androidx.lifecycle.ViewModel
import com.delarosa.composeapp.song.data.MainRepository
import com.delarosa.composeapp.song.domain.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(repository: MainRepository) : ViewModel() {

    private val _getSongListFlow = MutableStateFlow(repository.getSongList)
    var getSongListFlow: StateFlow<List<Song>> = _getSongListFlow

}

