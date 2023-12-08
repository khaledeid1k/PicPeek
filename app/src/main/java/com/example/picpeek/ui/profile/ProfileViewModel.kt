package com.example.picpeek.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picpeek.data.repo.Repo
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.domain.model.UserDetails
import com.example.picpeek.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor (private val repo: Repo): ViewModel(){
    private val _userDetails = MutableStateFlow<ApiState<UserDetails>>(ApiState.Loading)
    val userDetails: StateFlow<ApiState<UserDetails>> = _userDetails

    private val _userAlbums = MutableStateFlow<ApiState<List<UserAlbums>>>(ApiState.Loading)
    val     userAlbums: StateFlow<ApiState<List<UserAlbums>>> = _userAlbums


    private val randomUserId = Random.nextInt(1, 9)

    init {
        getSingleUser()
        getUserAlbums()
    }

    private fun getSingleUser() {
        viewModelScope.launch {
            repo.getSingleUser(randomUserId).collect {
                _userDetails.value = it
            }
        }

    }

    private fun getUserAlbums() {
        viewModelScope.launch {
            repo.getUserAlbums(randomUserId).collect {
                _userAlbums.value = it
            }
        }

    }

    fun retryToConnectNetWork() {
        getSingleUser()
        getUserAlbums()
    }


}