package com.example.checklistbygleb.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.R
import com.example.checklistbygleb.domain.CheckItem

class CheckListAdapter : ListAdapter<CheckItem, CheckListAdapter.CheckItemViewHolder>(CheckItemDiffCallback()) {

    var onCheckItemClickListener: ((CheckItem) -> Unit)? = null
    var onCheckItemLongClickListener: ((CheckItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CheckItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckItemViewHolder, position: Int) {
        val checkItem = getItem(position)
        with(holder) {
            tvName.text = checkItem.name
            tvCount.text = checkItem.count.toString()
            view.setOnClickListener {
                onCheckItemClickListener?.invoke(checkItem)
            }
            view.setOnLongClickListener {
                onCheckItemLongClickListener?.invoke(checkItem)
                true
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

    class CheckItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCount = view.findViewById<TextView>(R.id.tvCount)
    }

    companion object {

        const val VIEW_TYPE_ENABLED = R.layout.item_shop_enabled
        const val VIEW_TYPE_DISABLED = R.layout.item_shop_disabled
        const val MAX_POOL_SIZE = 10
    }

}