package com.example.grocerylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.grocerylist.pantry.PantryActivity
import com.example.grocerylist.list.ListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groceryListButton = findViewById<Button>(R.id.grocery_list_button)
        val pantryButton = findViewById<Button>(R.id.pantry_button)
        val nearbyStoresButton = findViewById<Button>(R.id.nearby_grocery_stores_button)

        groceryListButton.setOnClickListener { openGroceryList() }
        pantryButton.setOnClickListener { openPantry() }
        nearbyStoresButton.setOnClickListener { openNearbyStores() }
    }

    private fun openGroceryList() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
    private fun openPantry() {
        val intent = Intent(this, PantryActivity::class.java)
        startActivity(intent)
    }
    private fun openNearbyStores() {
        val intent = Intent(this, NearbyGroceryStoresActivity::class.java)
        startActivity(intent)
    }

}