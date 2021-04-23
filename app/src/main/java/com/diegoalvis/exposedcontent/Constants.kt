package com.diegoalvis.exposedcontent

import android.net.Uri

/**
 * Created by Android on 02/17/2018.
 */
object Constants {
    private const val authorityPackageNameBase = "com.diegoalvis.exposedcontent.sourceApp"
    private fun String.toContentUri() = Uri.parse("content://$this/t1")
    val AUTHORITY_PERMISSION_SIGNATURE = "$authorityPackageNameBase.provider.permission.signature"
    val AUTHORITY_PERMISSION_PUBLIC_DB = "$authorityPackageNameBase.provider.permission.public"
    val AUTHORITY_PUBLIC = "$authorityPackageNameBase.provider.public"

    val URL_PERMISSION_SIGNATURE = AUTHORITY_PERMISSION_SIGNATURE.toContentUri()
    val URL_PERMISSION_PUBLIC_DB = AUTHORITY_PERMISSION_PUBLIC_DB.toContentUri()
    val URL_PUBLIC = AUTHORITY_PUBLIC.toContentUri()
//    val URL_PERMISSION_SIGNATURE = Uri.parse("content://$AUTHORITY_PERMISSION_SIGNATURE/t1")
//    val URL_PERMISSION_PUBLIC = Uri.parse("content://$AUTHORITY_PERMISSION_PUBLIC/t1")
//    val URL_PUBLIC = Uri.parse("content://$AUTHORITY_PUBLIC/t1")
    val CONTENT_TYPE = "contentproviderlab.t12"

    val dbColumnNameId = "_id"
    val dbColumnNameText = "MESSAGE"

    val columnNameType = "TYPE"
    val columnNameData = "DATA"
}
