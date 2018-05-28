package rax.akash.quickbaserecyclerview.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import rax.akash.quickbaserecyclerview.util.inflate

/**
 * Created by Akash Saggu(R4X) on 03-04-2018.
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>(
        @LayoutRes private val view: Int,
        private val holderclass: Class<VH>,
        protected val list: ArrayList<T> = arrayListOf()) : RecyclerView.Adapter<VH>()/*, View.OnClickListener*/ {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent, view)
    }

    protected open fun getViewHolder(parent: ViewGroup?, vieww: Int): VH {
        val view = parent!! inflate vieww

        val constructor = holderclass.getConstructor(View::class.java)
        return constructor.newInstance(view)
    }


    override fun getItemCount() = list.size


    abstract override fun onBindViewHolder(holder: VH, position: Int)

    fun addToList(list: ArrayList<T>) {
        val previousRange = list.size

        list.addAll(list)

        val newRange = list.size
        notifyItemChanged(previousRange, newRange)
    }

    fun addNewList(list: ArrayList<T>) {
        this@BaseAdapter.list.clear()
        this@BaseAdapter.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addItemToList(item: T) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addNewList(list: Iterable<T>) {
        this@BaseAdapter.list.clear()
        this@BaseAdapter.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addMoreItems(items: List<T>) {
        val previousRange = list.size

        list.addAll(items)

        val newRange = list.size

        notifyItemRangeInserted(previousRange, newRange)


    }

    fun appendAtStart(item: T) {
        list.remove(item)
        list.add(0, item)
        notifyItemInserted(0)
    }


    fun getOriginalList(): ArrayList<T> {
        return list
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}