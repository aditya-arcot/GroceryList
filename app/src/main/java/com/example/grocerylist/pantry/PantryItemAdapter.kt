package com.example.grocerylist.pantry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.R

class PantryItemAdapter (
    private val context: Context,
    private val checkBoxClick: (Any) -> Unit

): RecyclerView.Adapter<PantryItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameTextView: TextView = view.findViewById(R.id.pantry_item_name)
        val infoTextView: TextView = view.findViewById(R.id.pantry_item_info)
        val checkBox: CheckBox = view.findViewById(R.id.pantry_item_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.pantry_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.nameTextView.text = item.pantryItemName
        holder.infoTextView.text = item.pantryItemInfo
        holder.checkBox.isChecked = item.checked

        holder.checkBox.setOnClickListener {
            data.remove(item)
            notifyDataSetChanged()
            checkBoxClick("")
        }
    }

    override fun getItemCount() = data.size

}



