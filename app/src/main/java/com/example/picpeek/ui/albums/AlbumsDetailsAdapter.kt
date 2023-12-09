package com.example.picpeek.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.picpeek.R
import com.example.picpeek.databinding.ItemAlbumDetailsBinding
import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.ui.base.BaseDataDiffUtil

class AlbumsDetailsAdapter(private val albumsDetailsListener: AlbumsDetailsListener)  : RecyclerView.Adapter<AlbumsDetailsAdapter.AlbumsDetailsViewHolder>() {

    private var albumsDetails: List<AlbumsDetails> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsDetailsViewHolder {
        return AlbumsDetailsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_album_details,
                parent, false)
        )
    }
    override fun onBindViewHolder(holder: AlbumsDetailsViewHolder, position: Int) {
        val albumsDetail = albumsDetails[position]
        holder.bind(albumsDetail,albumsDetailsListener)
    }
    override fun getItemCount()=albumsDetails.size

    class AlbumsDetailsViewHolder(private val binding: ItemAlbumDetailsBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(albumsDetail: AlbumsDetails,albumsDetailsListener :AlbumsDetailsListener) {
            binding.item = albumsDetail
            binding.listener = albumsDetailsListener
        }
    }

    fun setItems(newItems: List<AlbumsDetails>) {
        val diffResult = DiffUtil.calculateDiff(
            BaseDataDiffUtil(albumsDetails, newItems,
                checkItemsTheSame=    { oldItemPosition, newItemPosition -> albumsDetails[oldItemPosition].title == newItems[newItemPosition].title },
                checkContentsTheSame=  { oldItemPosition, newItemPosition -> albumsDetails[oldItemPosition] == newItems[newItemPosition] }
            )
        )
        albumsDetails = newItems
        diffResult.dispatchUpdatesTo(this)
    }
    interface AlbumsDetailsListener { fun onClickAlbumPhoto(albumsDetails: AlbumsDetails) }



}
