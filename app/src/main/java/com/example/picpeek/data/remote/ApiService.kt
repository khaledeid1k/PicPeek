package com.example.picpeek.data.remote

import com.example.picpeek.data.remote.model.abums_details.AlbumsDetailsDto
import com.example.picpeek.data.remote.model.albums.UserAlbumsDto
import com.example.picpeek.data.remote.model.user_details.UserDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{userId}")
    suspend fun getSingleUser(@Path("userId") userId: Int): Response<UserDetailsDto>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(@Path("userId") userId: Int): Response<UserAlbumsDto>

    @GET("albums/{albumId}/photos")
    suspend  fun getAlbumsDetails(@Path("albumId") albumId: Int): Response<AlbumsDetailsDto>
}