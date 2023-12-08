package com.example.picpeek.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.picpeek.R
import com.example.picpeek.databinding.ItemUserAlbumBinding
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.base.BaseDataDiffUtil

class AlbumsUserAdapter(private val albumsUserListener: AlbumsUserListener)  : RecyclerView.Adapter<AlbumsUserAdapter.AlbumsUserViewHolder>() {

    private var userAlbums: List<UserAlbums> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsUserViewHolder {
        return AlbumsUserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_user_album,
                parent, false)
        )
    }
    override fun onBindViewHolder(holder: AlbumsUserViewHolder, position: Int) {
        val userAlbum = userAlbums[position]
        holder.bind(userAlbum,albumsUserListener)
    }
    override fun getItemCount()=userAlbums.size

    class AlbumsUserViewHolder (private val binding: ItemUserAlbumBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(userAlbum: UserAlbums,albumsUserListener :AlbumsUserListener) {
            binding.userAlbum = userAlbum
            binding.albumsUserListener = albumsUserListener
        }
    }

    fun setItems(newItems: List<UserAlbums>) {
        val diffResult = DiffUtil.calculateDiff(
            BaseDataDiffUtil(userAlbums, newItems,
                checkItemsTheSame=    { oldItemPosition, newItemPosition -> userAlbums[oldItemPosition].title == newItems[newItemPosition].title },
                checkContentsTheSame=  { oldItemPosition, newItemPosition -> userAlbums[oldItemPosition] == newItems[newItemPosition] }
            )
        )
        userAlbums = newItems
        diffResult.dispatchUpdatesTo(this)
    }
    interface AlbumsUserListener { fun onClickAlbum(albumId: Int) }



}
