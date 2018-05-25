package com.diegoalvis.exposedcontent

import android.net.Uri

/**
 * Created by Android on 02/17/2018.
 */
object Constants {
    val AUTHORITY = "com.androidatc.exposedcontent.provider"
    val URL = Uri.parse("content://" + AUTHORITY + "/t1")
    val CONTENT_TYPE = "contentproviderlab.t12"
    val ID = "_ID"
    val TEXT = "MESSAGE"
    val TEXT_DATA = "Hello Content Providers!"
}
