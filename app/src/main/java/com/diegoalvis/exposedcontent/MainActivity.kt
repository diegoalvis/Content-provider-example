package com.diegoalvis.exposedcontent

import android.app.Activity
import android.content.ContentValues
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signature = findViewById<TextView>(R.id.signature)
        signature.text = getSignature()
    }

    private fun getSignature(): String {
        val context = baseContext
        val packageManager = context.packageManager

        @Suppress("DEPRECATION")
        val sigs: Array<Signature> = packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES).signatures
        return sigs[0].hashCode().toString()
    }

    override fun onStart() {
        super.onStart()

        // Inserting data into content provider
        findViewById<TextView>(R.id.textViewPermissionPublicInsert).text = try {
            val tuple = ContentValues()
            tuple.put(Constants.dbColumnNameText, "Hello Content Providers!")
            contentResolver.insert(Constants.URL_PERMISSION_PUBLIC_DB, tuple)
            "Insert success"
        } catch (e: Exception) {
            "Insert Error: $e"
        }

        fun queryContentProvider(uri: Uri, projection: Array<out String>): String {
            return try {
                // Reading from content provider with auto-close after use
                contentResolver.query(uri, projection, null, null, null)
                        .use { c ->
                            if (c?.moveToLast() == true) {
                                val col0 = projection[0]
                                val type = c.getString(c.getColumnIndex(col0))
                                val col1 = projection[1]
                                val data = c.getString(c.getColumnIndex(col1))
                                "Data read from content provider: $col0=$type, $col1=$data"
                            } else "Access denied"
                        }
            } catch (e: Exception) {
                Log.e("Main", "error", e)
                "Error: $e"
            }
        }

        val staticProviderColumns = arrayOf(Constants.columnNameType, Constants.columnNameData)

        findViewById<TextView>(R.id.textViewSignature).text = queryContentProvider(Constants.URL_PERMISSION_SIGNATURE, staticProviderColumns)
        findViewById<TextView>(R.id.textViewPublic).text = queryContentProvider(Constants.URL_PUBLIC, staticProviderColumns)

        val dbColumns = arrayOf(DBHelper.dbColumnNameId, DBHelper.dbColumnNameText)
        findViewById<TextView>(R.id.textViewPermissionPublic).text = queryContentProvider(Constants.URL_PERMISSION_PUBLIC_DB, dbColumns)
    }
}

