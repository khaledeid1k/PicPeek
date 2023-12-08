package com.example.picpeek.data.remote.model.user_details

data class AddressDto(
    val city: String?,
    val geo: Geo?,
    val street: String?,
    val suite: String?,
    val zipcode: String?
)