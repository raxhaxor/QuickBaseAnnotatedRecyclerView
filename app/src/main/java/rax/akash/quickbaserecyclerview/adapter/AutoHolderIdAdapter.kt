package rax.akash.quickbaserecyclerview.adapter

import android.widget.TextView
import rax.akash.api.AutoHolder
import rax.akash.api.ViewType
import rax.akash.quickbaserecyclerview.R
import rax.akash.quickbaserecyclerview.model.Name

/**
 * Created by Akash Saggu(R4X) on 28-05-2018 at 19:35.
 * akashsaggu@protonmail.com
 */
@AutoHolder(
        views = [
            ViewType(id = R.id.name, name = "name", type = TextView::class)
        ]
)
class AutoHolderIdAdapter : BaseAdapter<AutoHolderIdAdapterVh, Name>(R.layout.item_recyclerview, AutoHolderIdAdapterVh::class.java) {

    override fun onBindViewHolder(holder: AutoHolderIdAdapterVh, position: Int) {
        holder.name.text = list[position].name
    }

}