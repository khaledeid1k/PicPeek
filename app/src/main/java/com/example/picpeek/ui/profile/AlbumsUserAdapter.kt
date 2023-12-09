package com.example.picpeek.ui.profile

import com.example.picpeek.R
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.base.BaseAdapter
import com.example.picpeek.ui.base.BaseListener

class AlbumsUserAdapter( albumsUserListener: AlbumsUserListener):
BaseAdapter<UserAlbums>(albumsUserListener) {
    override val layoutIdFragment=R.layout.item_user_album
    interface AlbumsUserListener:BaseListener { fun onClickAlbum(userAlbum: UserAlbums) }


}
