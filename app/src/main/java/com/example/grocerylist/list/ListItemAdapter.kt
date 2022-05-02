package com.example.grocerylist.list

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.R
import com.example.grocerylist.SharedPreferencesFunctions
import com.example.grocerylist.pantry.PantryItem


class ListItemAdapter(
    private val context: Context,
): RecyclerView.Adapter<ListItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.list_item_name)
        val checkBox: CheckBox = view.findViewById(R.id.list_item_checkbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    private fun strikethrough(textView: TextView, bool: Boolean){
        if (bool){
            textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.listItemName
        holder.checkBox.isChecked = item.checked
        strikethrough(holder.textView, holder.checkBox.isChecked)

        holder.textView.setOnLongClickListener {
            Toast.makeText(context, "Edit item", Toast.LENGTH_SHORT).show()

            val editText = (context as ListActivity).findViewById<EditText>(R.id.list_input)
            editText.setText(item.listItemName)
            removeItem(item)

            true
        }

        holder.checkBox.setOnClickListener {
            item.checked = !item.checked
            strikethrough(holder.textView, holder.checkBox.isChecked)

            val options = ArrayList<String>()
            options.add("Remove")
            options.add("Move to pantry")


            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(item.listItemName)
            builder.setItems(options.toTypedArray()) { _, position ->
                if (position != 2) {
                    removeItem(item)

                    if (position == 1) {
                        addToPantry(item)
                    }
                }

            }
            builder.show()
        }
    }

    private fun removeItem(item: ListItem){
        data.remove(item)
        SharedPreferencesFunctions.saveGroceryList(data, sharedPrefs)

        notifyDataSetChanged()
    }

    private fun addToPantry(item: ListItem){
        val pantry_item = PantryItem(false, item.listItemName, "")
        var pantry = SharedPreferencesFunctions.loadPantry(sharedPrefs)
        if (pantry != null) {
            pantry.add(pantry_item)
        }
        else {
            pantry = ArrayList()
            pantry.add(pantry_item)
        }
        SharedPreferencesFunctions.savePantry(pantry, sharedPrefs)
    }

    override fun getItemCount() = data.size

}



