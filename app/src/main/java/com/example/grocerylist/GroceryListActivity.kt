package com.example.grocerylist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.adapter.ItemAdapter
import com.example.grocerylist.data.Datasource
import com.example.grocerylist.model.ListItem

class GroceryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_list)

        val myDataset = Datasource().loadListItems()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = ItemAdapter(this, myDataset) {
                item -> openItemInfoActivity(item)
        }
    }

    fun openItemInfoActivity(item: ListItem) {
        val intent = Intent(this, ItemInfoActivity::class.java).apply {
            putExtra("name", resources.getString(item.listItemName))
        }
        startActivity(intent)
    }
}