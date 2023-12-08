package com.example.picpeek.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picpeek.R
import com.example.picpeek.domain.model.UserAlbums
import com.example.picpeek.ui.profile.AlbumsUserAdapter

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url).error(R.drawable.ic_launcher_background)
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

@BindingAdapter("app:showWhenSuccess")
fun <T> View.showWhenSuccess(apiSate: ApiState<T>) {
    if (apiSate is ApiState.Success) makeVisible() else makeGone()

}


@BindingAdapter(value = ["adapterRe", "items"], requireAll = false)
fun RecyclerView.setAdapter(adapterRe: AlbumsUserAdapter, items: List<UserAlbums>?) {
    adapter=adapterRe
    items?.let { adapterRe.setItems(it) }

}


