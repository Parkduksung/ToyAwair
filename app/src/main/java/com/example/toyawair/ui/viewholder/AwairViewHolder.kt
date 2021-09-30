package com.example.toyawair.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.toyawair.BR
import com.example.toyawair.R
import com.example.toyawair.api.response.AwairEvent
import com.example.toyawair.databinding.ItemAwairBinding

class AwairViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_awair, parent, false)
) {

    private val binding = DataBindingUtil.bind<ItemAwairBinding>(itemView)


    fun bind(item: AwairEvent) {
        binding?.run {
            setVariable(BR.event, item)
            executePendingBindings()
        }

    }

}