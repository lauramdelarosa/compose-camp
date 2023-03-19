package com.delarosa.composeapp.album.presentation

import androidx.lifecycle.ViewModel
import com.delarosa.composeapp.album.data.AlbumRepository
import com.delarosa.composeapp.album.domain.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlbumViewModel(repository: AlbumRepository) : ViewModel() {
    private val _getAlbumListFlow = MutableStateFlow(repository.getAlbumList)
    var getAlbumListFlow: StateFlow<List<Album>> = _getAlbumListFlow
}
