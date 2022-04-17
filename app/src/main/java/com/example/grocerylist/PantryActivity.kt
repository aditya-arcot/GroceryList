package com.example.grocerylist
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.adapter.ItemAdapter
import com.example.grocerylist.data.Datasource
import com.example.grocerylist.model.ListItem
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity


class PantryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry)
    }

}