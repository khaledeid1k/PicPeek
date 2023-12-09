package com.example.picpeek.ui.albums

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.picpeek.R
import com.example.picpeek.databinding.FragmentAlbumDetailsBinding
import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : BaseFragment<FragmentAlbumDetailsBinding>(),
    AlbumsDetailsAdapter.AlbumsDetailsListener {
    override val layoutIdFragment = R.layout.fragment_album_details
    override val viewModel: AlbumDetailsViewModel by viewModels()
    private val userAlbums: UserAlbums by lazy { receiveUserAlbums() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            title = userAlbums.title
            adapter = AlbumsDetailsAdapter(this@AlbumDetailsFragment)
        }
        sendAlbumsId()
    }

    private fun receiveUserAlbums() =
        AlbumDetailsFragmentArgs.fromBundle(requireArguments()).userAlbums

    private fun sendAlbumsId() {
        viewModel.apply {
            saveAlbumId(userAlbums.id)
            getAlbumsDetails()
        }
    }

    override fun onClickAlbumPhoto(albumsDetails: AlbumsDetails) {

    }

}