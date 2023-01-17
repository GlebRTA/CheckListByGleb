package com.example.checklistbygleb.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.checklistbygleb.domain.CheckItem

class CheckItemDiffCallback : DiffUtil.ItemCallback<CheckItem>() {
    override fun areItemsTheSame(oldItem: CheckItem, newItem: CheckItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CheckItem, newItem: CheckItem): Boolean {
        return oldItem == newItem
    }
}