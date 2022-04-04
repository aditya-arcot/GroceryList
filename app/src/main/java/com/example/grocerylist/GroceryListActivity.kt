package com.example.grocerylist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.adapter.ItemAdapter
import com.example.grocerylist.data.Datasource

class GroceryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_list)

        // Initialize data
        val myDataset = Datasource().loadListItems()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        //recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.adapter = ItemAdapter(this, myDataset) {
                item -> Toast.makeText(this, item.listItemName, Toast.LENGTH_SHORT).show()
        }
    }


}