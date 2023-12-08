package com.example.picpeek.data.repo

import android.util.Log
import com.example.picpeek.data.remote.ApiService
import com.example.picpeek.domain.mapper.UserAlbumsMapper
import com.example.picpeek.domain.mapper.UserDetailsMapper
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.domain.model.UserDetails
import com.example.picpeek.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RepoImp @Inject constructor(
    private val service: ApiService,
    private val userDetailsMapper: UserDetailsMapper,
    private val userAlbumsMapper: UserAlbumsMapper
) : Repo {
    override suspend fun getSingleUser(userId: Int): Flow<ApiState<UserDetails>> {
        return wrapResponse({ service.getSingleUser(userId) }, userDetailsMapper::map)
    }

    override suspend fun getUserAlbums(userId: Int): Flow<ApiState<List<UserAlbums>>> {
        return wrapResponse({ service.getUserAlbums(userId) }, userAlbumsMapper::map)
    }


    private suspend fun <T, O> wrapResponse(
        function: suspend () -> Response<T>, mapper: (T) -> O
    ): Flow<ApiState<O>> {
        return flow {
            emit(ApiState.Loading)
            val response = function()
            if (response.isSuccessful) {
                response.body()?.let {
                    val baseResponse = mapper(it)
                    emit(ApiState.Success(baseResponse))
                } ?: emit(ApiState.Error("Invalid Data"))

            } else {
                emit(ApiState.Error(response.errorBody()?.string() ?: "Network Error"))
            }
        }.catch {
            Log.d("adasdadsadas", ":${it.message} asdasdsad")
            emit(ApiState.Error("Server Error${it.message}"))
        }

    }
}