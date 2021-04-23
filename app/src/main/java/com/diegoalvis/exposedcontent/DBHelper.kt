package com.diegoalvis.exposedcontent

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context,
               name: String?,
               factory: SQLiteDatabase.CursorFactory?,
               version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(_db: SQLiteDatabase?) {
        _db?.execSQL("CREATE TABLE $tableName (_id INTEGER PRIMARY KEY AUTOINCREMENT, $dbColumnNameText VARCHAR(20))")
    }

    override fun onUpgrade(_db: SQLiteDatabase?, _oldVersion: Int, _newVersion: Int) {
        _db?.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(_db)
    }

    companion object {
        val tableName = "t1"
        val dbColumnNameId = Constants.dbColumnNameId
        val dbColumnNameText = Constants.dbColumnNameText
        val DATABASE_NAME = "table.db"
        val DATABASE_VERSION = 1
        fun createDatabase(context: Context) = DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
    }
}
