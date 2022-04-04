package com.example.grocerylist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GroceryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_list)

        val item_1_button = findViewById<Button>(R.id.item_1)
        val item_2_button = findViewById<Button>(R.id.item_2)

        item_1_button.setOnClickListener {
            openItemInfoActivity("Item 1")
        }
        item_2_button.setOnClickListener {
            openItemInfoActivity("Item 2")
        }
    }

    fun openItemInfoActivity(itemName:String) {
        val intent = Intent(this, ItemInfoActivity::class.java).apply {
            putExtra("itemName", itemName)
        }
        startActivity(intent)
    }

}