package com.diegoalvis.exposedcontent

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inserting data into content provider
        val tuple = ContentValues()
        tuple.put(Constants.TEXT, "Constants.TEXT_DATA")
        contentResolver.insert(Constants.URL, tuple)
        // Reading from content provider
        val cols = arrayOf(Constants.ID, Constants.TEXT)
        val u = Constants.URL
        val c = contentResolver.query(u, cols, null, null, null)
        if (c.moveToLast())
            textView.text = "Data read from content provider: " +
                    c.getString(c.getColumnIndex(Constants.TEXT))
        else
            textView.text = "Access denied"
    }
}

