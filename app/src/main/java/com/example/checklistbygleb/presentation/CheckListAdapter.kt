package com.example.checklistbygleb.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.R
import com.example.checklistbygleb.domain.CheckItem

class CheckListAdapter : RecyclerView.Adapter<CheckListAdapter.CheckItemViewHolder>() {
    var count = 0
    var checkList = listOf<CheckItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckItemViewHolder {
        Log.d("RecycleView", "Count = ${count++}")
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CheckItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckItemViewHolder, position: Int) {
        val checkItem = checkList[position]
        with(holder) {
            tvName.text = checkItem.name
            tvCount.text = checkItem.count.toString()
            view.setOnLongClickListener {
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return checkList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (checkList[position].enabled) {
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