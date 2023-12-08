package com.example.picpeek.data.remote.model.user_details

data class UserDetailsDto(
    val address: AddressDto?,
    val companyDto: CompanyDto,
    val email: String,
    val id: Int?,
    val name: String?,
    val phone: String,
    val username: String,
    val website: String
)