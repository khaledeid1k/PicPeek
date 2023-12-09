package com.example.picpeek.ui.albums

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
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val repo: Repo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _albumsDetail = MutableStateFlow<ApiState<List<AlbumsDetails>>>(ApiState.Loading)
    val albumsDetail: StateFlow<ApiState<List<AlbumsDetails>>> = _albumsDetail
    val searchQuery = MutableStateFlow("")

    fun saveAlbumId(userId: Int) {
        savedStateHandle[ALBUM_ID] = userId
    }

    private fun getAlbumId(): Int {
        return savedStateHandle[ALBUM_ID] ?: 0
    }


    fun getAlbumsDetails() {
        viewModelScope.launch {
            repo.getAlbumsDetails(getAlbumId()).collect {
                when (it) {
                    is ApiState.Error -> {
                        _albumsDetail.value = ApiState.Error(it.message)
                    }

                    is ApiState.Loading -> {
                        _albumsDetail.value = ApiState.Loading
                    }

                    is ApiState.Success -> {
                        searchQuery.collect {query->
                            processSearchQuery(query,it.data)
                        }

                        }



                    }
                }
            }
        }


    private suspend fun processSearchQuery(query: String, data: List<AlbumsDetails>?) {
        val toList = data?.asFlow()
            ?.filter { albums -> albums.title.contains(query) }
            ?.toList()
        _albumsDetail.value = ApiState.Success(toList)
    }



    fun retryToConnectNetWork() {
        getAlbumsDetails()
    }

}