package com.example.picpeek.ui.albums

import com.example.picpeek.R
import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.ui.base.BaseAdapter
import com.example.picpeek.ui.base.BaseListener

class AlbumsDetailsAdapter(albumsDetailsListener: AlbumsDetailsListener)  :
    BaseAdapter<AlbumsDetails>(albumsDetailsListener) {
    override val layoutIdFragment=R.layout.item_album_details
    interface AlbumsDetailsListener:BaseListener { fun onClickAlbumPhoto(albumsDetails: AlbumsDetails) }



}
