package com.example.grocerylist.data

import com.example.grocerylist.R
import com.example.grocerylist.model.ListItem

class Datasource () {
    fun loadListItems(): List<ListItem>{
        return listOf<ListItem>(
            ListItem(R.string.list_item1),
            ListItem(R.string.list_item2),
            ListItem(R.string.list_item3),
            ListItem(R.string.list_item4),
            ListItem(R.string.list_item5)
        )
    }
}