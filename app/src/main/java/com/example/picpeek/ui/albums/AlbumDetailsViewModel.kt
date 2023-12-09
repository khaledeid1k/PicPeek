package com.example.picpeek.ui.albums

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picpeek.data.repo.Repo
import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.util.ApiState
import com.example.picpeek.util.Constants.ALBUM_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val repo: Repo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _albumsDetail = MutableStateFlow<ApiState<List<AlbumsDetails>>>(ApiState.Loading)
    val albumsDetail: StateFlow<ApiState<List<AlbumsDetails>>> = _albumsDetail

    fun saveAlbumId(userId: Int) {
        savedStateHandle[ALBUM_ID] = userId
    }

    private fun getAlbumId(): Int {
        return savedStateHandle[ALBUM_ID] ?:0
    }


     fun getAlbumsDetails() {
        viewModelScope.launch {
            Log.d("TAG", "getAlbumsDetails:${getAlbumId()} ")
            repo.getAlbumsDetails(getAlbumId()).collect {
                _albumsDetail.value = it
            }
        }
    }

    fun retryToConnectNetWork() {
        getAlbumsDetails()
    }

}