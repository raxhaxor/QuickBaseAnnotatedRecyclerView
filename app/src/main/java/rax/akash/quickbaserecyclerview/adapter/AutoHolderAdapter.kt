package rax.akash.quickbaserecyclerview.adapter

import kotlinx.android.synthetic.main.item_recyclerview.view.*
import rax.akash.api.AutoHolder
import rax.akash.quickbaserecyclerview.R
import rax.akash.quickbaserecyclerview.model.Name

/**
 * Created by Akash Saggu(R4X) on 28-05-2018 at 19:01.
 * akashsaggu@protonmail.com
 */
@AutoHolder
class AutoHolderAdapter : BaseAdapter<AutoHolderAdapterVh, Name>(R.layout.item_recyclerview, AutoHolderAdapterVh::class.java) {

    override fun onBindViewHolder(holder: AutoHolderAdapterVh, position: Int) {
        holder.itemView.name.text = list[position].name
    }

}