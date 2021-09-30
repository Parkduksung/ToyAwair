package com.example.toyawair.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyawair.api.response.AwairEvent
import com.example.toyawair.ui.viewholder.AwairViewHolder

class AwairAdapter : RecyclerView.Adapter<AwairViewHolder>() {

    private val awairEventList = mutableListOf<AwairEvent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwairViewHolder =
        AwairViewHolder(parent)

    override fun onBindViewHolder(holder: AwairViewHolder, position: Int) {
        holder.bind(item = awairEventList[position])
    }

    override fun getItemCount(): Int =
        awairEventList.size

    fun addAll(list: List<AwairEvent>) {
        awairEventList.addAll(list)
        notifyDataSetChanged()
    }

}