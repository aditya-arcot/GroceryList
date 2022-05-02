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
import com.example.grocerylist.SharedPreferencesFunctions

lateinit var data: ArrayList<ListItem>
lateinit var sharedPrefs: SharedPreferences

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("custom", "Opened list activity")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val lst = SharedPreferencesFunctions.loadGroceryList(sharedPrefs)
        data = if (lst != null) lst else ArrayList()

        val adapter = ListItemAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler_view)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.list_add_button)
        val addField = findViewById<EditText>(R.id.list_input)
        addButton.setOnClickListener{ buttonClicked(addField, adapter)}

    }

    private fun buttonClicked(textField: EditText, adapter: ListItemAdapter){
        val text = textField.text.toString().replace("\n", " ")

        if (text != ""){
            textField.setText("")

            data.add(ListItem(false, text))
            adapter.notifyItemChanged(data.size - 1)

            SharedPreferencesFunctions.saveGroceryList(data, sharedPrefs)

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