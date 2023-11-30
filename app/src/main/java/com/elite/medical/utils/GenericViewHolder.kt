package com.elite.medical.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GenericViewHolder<T> internal constructor(
    private val binding: ViewBinding,
    private val expression: (T, ViewBinding) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T){
        expression(item,binding)
    }

}