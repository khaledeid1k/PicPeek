package com.example.picpeek.data.repo

import android.util.Log
import com.example.picpeek.data.remote.ApiService
import com.example.picpeek.domain.mapper.AlbumsDetailsMapper
import com.example.picpeek.domain.mapper.UserAlbumsMapper
import com.example.picpeek.domain.mapper.UserDetailsMapper
import com.example.picpeek.domain.model.AlbumsDetails
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
    private val userAlbumsMapper: UserAlbumsMapper,
    private val albumsDetailsMapper: AlbumsDetailsMapper
) : Repo {
    override suspend fun getSingleUser(userId: Int): Flow<ApiState<UserDetails>> {
        return wrapResponse(userDetailsMapper::map){ service.getSingleUser(userId) }
    }

    override suspend fun getUserAlbums(userId: Int): Flow<ApiState<List<UserAlbums>>> {
        return wrapResponse(userAlbumsMapper::map){ service.getUserAlbums(userId) }
    }

    override suspend fun getAlbumsDetails(albumId: Int): Flow<ApiState<List<AlbumsDetails>>> {
        return wrapResponse(albumsDetailsMapper::map){service.getAlbumsDetails(albumId)}
    }


    private suspend fun <T, O> wrapResponse(
       mapper: (T) -> O,  function: suspend () -> Response<T>
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
            emit(ApiState.Error("Server Error${it.message}"))
        }

    }
}