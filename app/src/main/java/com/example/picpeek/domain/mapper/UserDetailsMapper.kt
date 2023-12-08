package com.example.picpeek.domain.mapper

import com.example.picpeek.data.remote.model.user_details.UserDetailsDto
import com.example.picpeek.domain.model.UserDetails
import javax.inject.Inject

class UserDetailsMapper @Inject constructor(){
    fun map(input: UserDetailsDto): UserDetails {
        return input.let {
            UserDetails(
                it.address?.let {address->
                    address.city+", "+address.street+", "+address.suite+", "+address.zipcode
                }?:"Not Found Address",
                it.id?:0,
                it.name?:""
            )
        }
    }
}