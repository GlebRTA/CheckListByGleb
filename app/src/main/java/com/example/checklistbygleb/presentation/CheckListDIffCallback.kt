package com.example.checklistbygleb.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.checklistbygleb.domain.CheckItem

class CheckListDIffCallback(
    private val oldList: List<CheckItem>,
    private val newList: List<CheckItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}