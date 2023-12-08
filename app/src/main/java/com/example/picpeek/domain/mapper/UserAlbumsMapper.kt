package com.example.picpeek.domain.mapper

import com.example.picpeek.data.remote.model.albums.UserAlbumsDto
import com.example.picpeek.domain.model.UserAlbums
import javax.inject.Inject

class UserAlbumsMapper  @Inject constructor(){
    fun map(input: UserAlbumsDto): List<UserAlbums> {
     return input.map {
         UserAlbums(it.id,it.title)
     }
    }
}