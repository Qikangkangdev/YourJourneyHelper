package com.example.yourjourneyhelper.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.yourjourneyhelper.data.models.Tickets

class TicketsDiffUtil(
    private val oldList: List<Tickets>,
    private val newList: List<Tickets>
) :DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id &&
                oldItem.price == newItem.price &&
                oldItem.means == newItem.means &&
                oldItem.hours == newItem.hours &&
                oldItem.start == newItem.start
    }
}