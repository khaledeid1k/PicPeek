package com.example.picpeek.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picpeek.R
import com.example.picpeek.domain.model.AlbumsDetails
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.albums.AlbumsDetailsAdapter
import com.example.picpeek.ui.profile.AlbumsUserAdapter

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url)
        .into(this)
}

@BindingAdapter("app:showWhenLoading")
fun <T> View.showWhenLoading(apiSate: ApiState<T>) {
    if (apiSate is ApiState.Loading) makeVisible() else makeGone()
}

@BindingAdapter("app:showWhenError")
fun <T> View.showWhenError(apiSate: ApiState<T>) {
    if (apiSate is ApiState.Error) makeVisible() else makeGone()

}
@BindingAdapter("app:noResult")
fun <T> View.noResult(list: List<T>?) {
    if (list!=null&&list.isEmpty()) makeVisible() else makeGone()

}

@BindingAdapter("app:showWhenSuccess")
fun <T> View.showWhenSuccess(apiSate: ApiState<T>) {
    if (apiSate is ApiState.Success) makeVisible() else makeGone()

}

@BindingAdapter(value = ["adapterRe", "items"], requireAll = false)
fun RecyclerView.setAdapter(adapterRe: RecyclerView.Adapter<*>, items: List<*>?) {
    adapter = adapterRe
    when(adapterRe){
        is AlbumsDetailsAdapter->{  items?.let { adapterRe.setItems(it as List<AlbumsDetails>) }}
        is AlbumsUserAdapter->{  items?.let { adapterRe.setItems(it as List<UserAlbums>) }}
    }

}
