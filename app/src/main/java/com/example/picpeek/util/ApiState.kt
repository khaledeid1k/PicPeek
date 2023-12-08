package com.example.picpeek.util

sealed class ApiState <out T> {
    data class Success<T>(val data: T?) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()

    fun toData(): T? = if (this is Success) data else null

}