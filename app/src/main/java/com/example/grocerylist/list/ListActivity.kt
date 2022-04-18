package com.example.grocerylist.list

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

lateinit var data: ArrayList<ListItem>
lateinit var sharedPrefs: SharedPreferences

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val lst = loadList()
        data = if (lst != null) lst else ArrayList()

        val adapter = ListItemAdapter(this){ checkBoxClick() }

        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler_view)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.list_add_button)
        val addField = findViewById<EditText>(R.id.list_input)
        addButton.setOnClickListener{ buttonClicked(addField, adapter)}

    }

    private fun checkBoxClick(){
        saveList(data)
    }

    private fun saveList(lst: ArrayList<ListItem>){
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(lst)
        editor.putString("grocery_list", json)
        editor.apply()

        Log.i("user", "saved: $lst")
    }

    private fun loadList(): ArrayList<ListItem>? {
        val gson = Gson()
        val json = sharedPrefs.getString("grocery_list", "")
        val type: Type = object : TypeToken<ArrayList<ListItem?>?>() {}.type
        val lst: ArrayList<ListItem>? = gson.fromJson(json, type)

        Log.i("user", "loaded: $lst")

        return lst
    }

    private fun buttonClicked(textField: EditText, adapter: ListItemAdapter){
        val text = textField.text.toString().replace("\n", " ")

        if (text != ""){
            textField.setText("")

            data.add(ListItem(false, text))
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

    /*private fun openItemInfoActivity(item: ListItem) {
        val intent = Intent(this, ItemInfoActivity::class.java).apply {
            putExtra("name", resources.getString(item.listItemName))
        }
        startActivity(intent)
    }*/
}