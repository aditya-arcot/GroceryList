package com.example.grocerylist.pantry

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.R
import com.example.grocerylist.SharedPreferencesFunctions
import com.example.grocerylist.list.ListItem

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

    private fun strikethrough(nameTextView: TextView, infoTextView: TextView, bool:Boolean){
        if (bool){
            nameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            infoTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            nameTextView.paintFlags = nameTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            infoTextView.paintFlags = infoTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.nameTextView.text = item.pantryItemName
        holder.infoTextView.text = item.pantryItemInfo
        holder.checkBox.isChecked = item.checked
        strikethrough(holder.nameTextView, holder.infoTextView, holder.checkBox.isChecked)

        holder.checkBox.setOnClickListener {
            item.checked = !item.checked
            strikethrough(holder.nameTextView,holder.infoTextView, holder.checkBox.isChecked)

            val options = ArrayList<String>()
            options.add("Strikethrough")
            options.add("Delete")



            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(item.pantryItemName)
            builder.setItems(options.toTypedArray()) { _, position ->
                if (position == 1) {
                    removeItem(item)


                }

            }
            builder.show()


        }
    }
    private fun removeItem(item: PantryItem){
        data.remove(item)

        SharedPreferencesFunctions.savePantry(data, sharedPrefs)


        notifyDataSetChanged()
    }


    override fun getItemCount() = data.size

}



