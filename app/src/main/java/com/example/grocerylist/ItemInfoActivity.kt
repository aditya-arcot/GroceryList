package com.example.grocerylist

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemInfoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val nameField = findViewById<TextView>(R.id.item_name)
        nameField.text = intent.getStringExtra("name")
    }

}