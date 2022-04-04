package com.example.grocerylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_button = findViewById<Button>(R.id.list_button)
        val nearest_store_button = findViewById<Button>(R.id.nearest_store_button)
        val profile_button = findViewById<Button>(R.id.profile_button)

        list_button.setOnClickListener {
            openListActivity()
        }
        nearest_store_button.setOnClickListener {
            openNearestStoreActivity()
        }
        profile_button.setOnClickListener {
            openProfileActivity()
        }

    }

    fun openListActivity() {
        val intent = Intent(this, GroceryListActivity::class.java)
        startActivity(intent)
    }
    fun openNearestStoreActivity() {
        val intent = Intent(this, NearestGroceryStoreActivity::class.java)
        startActivity(intent)
    }
    fun openProfileActivity() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
    }

}