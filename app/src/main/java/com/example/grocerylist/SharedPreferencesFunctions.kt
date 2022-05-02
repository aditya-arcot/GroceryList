package com.example.grocerylist

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.grocerylist.list.ListItem
import com.example.grocerylist.pantry.PantryItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPreferencesFunctions {
    companion object {
        fun saveGroceryList(lst: ArrayList<ListItem>, sharedPrefs: SharedPreferences){
            val editor = sharedPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(lst)
            editor.putString("grocery_list", json)
            editor.apply()

            Log.i("custom", "grocery list saved: $lst")
        }
        fun savePantry(lst: ArrayList<PantryItem>, sharedPrefs: SharedPreferences){
            val editor = sharedPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(lst)
            editor.putString("pantry", json)
            editor.apply()

            Log.i("custom", "pantry saved: $lst")
        }

        fun loadGroceryList(sharedPrefs: SharedPreferences): ArrayList<ListItem>? {
            val gson = Gson()
            val json = sharedPrefs.getString("grocery_list", "")
            val type: Type = object : TypeToken<ArrayList<ListItem?>?>() {}.type
            val lst: ArrayList<ListItem>? = gson.fromJson(json, type)

            Log.i("custom", "grocery list loaded: $lst")

            return lst
        }
        fun loadPantry(sharedPrefs: SharedPreferences): ArrayList<PantryItem>? {
            val gson = Gson()
            val json = sharedPrefs.getString("pantry", "")
            val type: Type = object : TypeToken<ArrayList<PantryItem?>?>() {}.type
            val lst: ArrayList<PantryItem>? = gson.fromJson(json, type)

            Log.i("custom", "pantry loaded: $lst")

            return lst
        }

    }
}