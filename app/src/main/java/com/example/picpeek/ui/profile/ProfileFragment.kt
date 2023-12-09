package com.example.picpeek.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picpeek.R
import com.example.picpeek.databinding.FragmentProfileBinding
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :BaseFragment<FragmentProfileBinding>(),
    AlbumsUserAdapter.AlbumsUserListener {
    override val layoutIdFragment=R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            adapter= AlbumsUserAdapter(this@ProfileFragment)

        }
    }

    override fun onClickAlbum(userAlbum: UserAlbums) {
        findNavController().navigate(
            ProfileFragmentDirections.actionProfileFragmentToAlbumDetailsFragment(userAlbum)
        )
    }

}