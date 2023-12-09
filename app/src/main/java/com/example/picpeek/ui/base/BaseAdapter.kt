package com.example.picpeek.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T>(private val baseListener: BaseListener) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>(){
    private var items: List<T> = emptyList()

    protected abstract val layoutIdFragment: Int

    fun setItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(
            BaseDataDiffUtil(items, newItems,
                checkItemsTheSame=    { oldItemPosition, newItemPosition -> items[oldItemPosition] === newItems[newItemPosition] },
                checkContentsTheSame=  { oldItemPosition, newItemPosition -> items[oldItemPosition] == newItems[newItemPosition] }
            )
        )
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutIdFragment,
                parent, false
            )
        )
    }

    override fun getItemCount()=items.size
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item,baseListener)
    }
    class BaseViewHolder<T>(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: T,
                 baseListener: BaseListener ) {
            binding.apply {
                setVariable(BR.item,items)
                setVariable(BR.listener,baseListener)
            }
        }
    }

}


interface BaseListener
