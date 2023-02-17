package com.example.checklistbygleb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.R
import com.example.checklistbygleb.databinding.ItemShopDisabledBinding
import com.example.checklistbygleb.databinding.ItemShopEnabledBinding
import com.example.checklistbygleb.domain.entity.CheckItem

class CheckListAdapter : ListAdapter<CheckItem, CheckListAdapter.CheckItemViewHolder>(CheckItemDiffCallback()) {

    var onCheckItemClickListener: ((CheckItem) -> Unit)? = null
    var onCheckItemLongClickListener: ((CheckItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckItemViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return CheckItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckItemViewHolder, position: Int) {
        val checkItem = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            onCheckItemClickListener?.invoke(checkItem)
        }
        binding.root.setOnLongClickListener {
            onCheckItemLongClickListener?.invoke(checkItem)
            true
        }
        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.tvName.text = checkItem.name
                binding.tvCount.text = checkItem.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.tvName.text = checkItem.name
                binding.tvCount.text = checkItem.count.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class CheckItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        const val VIEW_TYPE_ENABLED = R.layout.item_shop_enabled
        const val VIEW_TYPE_DISABLED = R.layout.item_shop_disabled
        const val MAX_POOL_SIZE = 10
    }

}