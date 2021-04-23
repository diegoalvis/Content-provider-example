package com.diegoalvis.exposedcontent

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.diegoalvis.exposedcontent.Constants.AUTHORITY_PERMISSION_PUBLIC_DB
import java.sql.SQLException


class DbDataProvider : ContentProvider() {

    private lateinit var dbHelper: DBHelper
    private val DATUM = 1
    private val DATUM_ID = 2
    private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(authority, tableName, DATUM)
        addURI(authority, "$tableName/#", DATUM_ID)
    }
    private var projMap = mutableMapOf<String, String>().apply {
        this[dbColumnNameId] = dbColumnNameId
        this[dbColumnNameText] = dbColumnNameText
    }

    override fun onCreate(): Boolean {
        dbHelper = DBHelper.createDatabase(context!!)
        return true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        if (sUriMatcher.match(uri) != DATUM) throw IllegalArgumentException("DbDataProvider Unsupported URI $uri")
        val v = if (cv != null) ContentValues(cv) else ContentValues()

        val db = dbHelper.writableDatabase
        val rId = db?.insert(tableName, dbColumnNameText, v)
        if (rId != null) {
            if (rId > 0) {
                val uriWithId = ContentUris.withAppendedId(Constants.URL_PERMISSION_SIGNATURE, rId)
                context?.contentResolver?.notifyChange(uriWithId, null)
                return uriWithId
            }
        } else {
            throw SQLException("Failed to insert row into $uri")
        }
        return null
    }

    override fun query(uri: Uri, p: Array<out String>?, s: String?, args: Array<out String>?, sort: String?): Cursor {
        val qb = SQLiteQueryBuilder()
        qb.tables = tableName
        qb.projectionMap = projMap
        var s1 = s
        val temp = "content://$authority/t1"
        if (Uri.parse(temp).toString() != temp)
            throw IllegalArgumentException("DbDataProvider mismatch 0\n$uri\n${Constants.URL_PERMISSION_PUBLIC_DB}")
        if (uri.toString() != Constants.URL_PERMISSION_PUBLIC_DB.toString())
            throw IllegalArgumentException("DbDataProvider mismatch 1\n$uri\n${Constants.URL_PERMISSION_PUBLIC_DB}")
        if (uri.toString() != Uri.parse("content://$AUTHORITY_PERMISSION_PUBLIC_DB/t1").toString()) throw IllegalArgumentException("DbDataProvider " +
                "mismatch 2")
        if (uri.toString() != Uri.parse("content://$authority/t1").toString()) throw IllegalArgumentException("DbDataProvider mismatch 3")
        if (sUriMatcher.match(uri) != DATUM) {
            if (sUriMatcher.match(uri) == DATUM_ID)
                s1 = "${s}_id = ${uri.lastPathSegment}"
            else
                throw IllegalArgumentException("DbDataProvider Unsupported URI $uri\nThis supports authority: $authority")
        }
        val db = dbHelper.readableDatabase
        val c = qb.query(db, p, s1, args, null, null, sort)
        c.setNotificationUri(context?.contentResolver, uri)
        return c
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 1
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 1
    }

    override fun getType(uri: Uri): String {
        if (sUriMatcher.match(uri) != DATUM) throw IllegalArgumentException("DbDataProvider Unsupported URI $uri")
        return Constants.CONTENT_TYPE
    }

    companion object {
        val authority = AUTHORITY_PERMISSION_PUBLIC_DB
        val dbColumnNameId = DBHelper.dbColumnNameId
        val dbColumnNameText = DBHelper.dbColumnNameText
        private val tableName = DBHelper.tableName
    }
}
