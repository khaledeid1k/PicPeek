package com.example.picpeek.domain.mapper

import com.example.picpeek.data.remote.model.abums_details.AlbumsDetailsDto
import com.example.picpeek.domain.model.AlbumsDetails
import javax.inject.Inject

class AlbumsDetailsMapper @Inject constructor(){
    fun map(albumsDetails: AlbumsDetailsDto): List<AlbumsDetails> {
        return albumsDetails.map {
            AlbumsDetails(
                it.title?:"",
                it.url?:""
            )
        }

    }
}