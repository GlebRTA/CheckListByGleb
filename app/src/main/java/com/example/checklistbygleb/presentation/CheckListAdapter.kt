package com.example.checklistbygleb.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.R
import com.example.checklistbygleb.domain.CheckItem

class CheckListAdapter : RecyclerView.Adapter<CheckListAdapter.CheckItemViewHolder>() {

    private val list = listOf<CheckItem>()

    class CheckItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCount = view.findViewById<TextView>(R.id.tvCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        return CheckItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckItemViewHolder, position: Int) {
        val checkItem = list[position]
        with(holder) {
            tvName.text = checkItem.name
            tvCount.text = checkItem.count.toString()
            view.setOnLongClickListener {
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}