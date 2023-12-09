package com.example.picpeek.data.repo

import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.domain.model.UserDetails
import com.example.picpeek.util.ApiState
import kotlinx.coroutines.flow.Flow

interface Repo {
     suspend  fun getSingleUser(userId: Int): Flow<ApiState<UserDetails>>
     suspend  fun getUserAlbums(userId: Int): Flow<ApiState<List<UserAlbums>>>
     suspend   fun getAlbumsDetails(albumId: Int): Flow<ApiState<List<AlbumsDetails>>>
}