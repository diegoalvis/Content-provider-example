package com.diegoalvis.exposedcontent

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log


/**
 * Example of data built with matrix cursor.
 */
open class StaticDataProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        Log.w(tag, "insert: called with $uri $cv")
        return null // unsupported no-op
    }

    override fun query(uri: Uri, p: Array<out String>?, selection: String?, args: Array<out String>?, sort: String?): Cursor? {
        Log.w(tag, "called with $uri, columns=${p?.toList()}, selection=$selection, args=${args?.toList()}, sort=$sort")
        val cursor = MatrixCursor(COLUMN_NAMES)
        fun add(type: String, json: String) {
            val rb = cursor.newRow()
            rb.add(columnNameType, type)
            rb.add(columnNameData, json)
        }
        add("APP_SETTINGS", "{}")
        add("TRIP", """{"trip":"aTrip"}""")
        return cursor
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0 // unsupported no-op
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0 // unsupported no-op
    }

    override fun getType(uri: Uri): String {
        return Constants.CONTENT_TYPE
    }

    companion object {
        private const val tag = "StaticDataProvider"
        val columnNameType = Constants.columnNameType
        val columnNameData = Constants.columnNameData
        private val COLUMN_NAMES: Array<String> = arrayOf(columnNameType, columnNameData)
    }
}
