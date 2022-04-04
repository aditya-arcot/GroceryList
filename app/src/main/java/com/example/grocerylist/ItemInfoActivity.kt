package com.example.grocerylist

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val item_text = findViewById<TextView>(R.id.item_text)
        item_text.text = intent.getStringExtra("itemName")
    }
}