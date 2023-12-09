package com.example.picpeek.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAlbums(
    val id: Int,
    val title: String
) : Parcelable