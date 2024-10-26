package com.example.singleadaptermultipleviewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.singleadaptermultipleviewholder.Database.VIEW_TYPE_HEADER
import com.example.singleadaptermultipleviewholder.Database.VIEW_TYPE_ITEM
import com.example.singleadaptermultipleviewholder.databinding.HeaderCardViewBinding
import com.example.singleadaptermultipleviewholder.databinding.ItemCardViewBinding

class MyAdapter(private val totalList: List<DataItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ItemViewHolder(val binding: ItemCardViewBinding) : RecyclerView.ViewHolder(binding.root)
    inner class HeaderViewHolder(val binding: HeaderCardViewBinding) : RecyclerView.ViewHolder(binding.root)


    override fun getItemViewType(position: Int): Int {

        when (totalList[position]) {
            is DataItem.Item -> return VIEW_TYPE_ITEM
            is DataItem.Header -> return VIEW_TYPE_HEADER
        }
    }

    override fun getItemCount(): Int {
        return totalList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = ItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            VIEW_TYPE_HEADER -> {
                val binding = HeaderCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)

            }
            else -> {throw IllegalArgumentException("Geçersiz view type")}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemViewHolder -> {
                val item = totalList[position] as DataItem.Item
                holder.binding.name.text = item.name
            }
            is HeaderViewHolder -> {
                val header = totalList[position] as DataItem.Header
                holder.binding.nameHeader.text = header.name
            }

        }
    }


}