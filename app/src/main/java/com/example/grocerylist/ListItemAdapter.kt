package com.example.grocerylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListItemAdapter (
    private val context: Context,
    private val checkBoxClick: (Any) -> Unit

): RecyclerView.Adapter<ListItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_name)
        val checkBox: CheckBox = view.findViewById(R.id.list_item_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.listItemName
        holder.checkBox.isChecked = item.checked

        holder.textView.setOnClickListener {}

        holder.checkBox.setOnClickListener {
            data.remove(item)
            notifyDataSetChanged()
            checkBoxClick("")
        }
    }

    override fun getItemCount() = data.size

}



