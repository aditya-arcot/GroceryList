package com.example.grocerylist.pantry

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

lateinit var data: ArrayList<PantryItem>
lateinit var sharedPrefs: SharedPreferences

class PantryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val lst = loadList()
        data = if (lst != null) lst else ArrayList()

        val adapter = PantryItemAdapter(this){ checkBoxClick() }

        val recyclerView = findViewById<RecyclerView>(R.id.pantry_recycler_view)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.pantry_add_button)
        val addField = findViewById<EditText>(R.id.pantry_input)
        addButton.setOnClickListener{ buttonClicked(addField, adapter)}

    }

    private fun checkBoxClick(){
        saveList(data)
    }

    private fun saveList(lst: ArrayList<PantryItem>){
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(lst)
        editor.putString("pantry", json)
        editor.apply()

        Log.i("user", "saved: $lst")
    }

    private fun loadList(): ArrayList<PantryItem>? {
        val gson = Gson()
        val json = sharedPrefs.getString("pantry", "")
        val type: Type = object : TypeToken<ArrayList<PantryItem?>?>() {}.type
        val lst: ArrayList<PantryItem>? = gson.fromJson(json, type)

        Log.i("user", "loaded: $lst")

        return lst
    }

    private fun buttonClicked(textField: EditText, adapter: PantryItemAdapter){
        val text = textField.text.toString().replace("\n", " ")

        if (text != ""){
            textField.setText("")

            data.add(PantryItem(false, text))
            adapter.notifyItemChanged(data.size - 1)

            saveList(data)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}