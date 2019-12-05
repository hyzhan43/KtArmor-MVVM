package com.zhan.mvvm.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 *  author: HyJame
 *  date:   2019-12-04
 *  desc:   根据 contextProvider, 无入侵式获取 applicationContext
 */
class ApplicationContextProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        ContextProvider.attachContext(context)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
            uri: Uri,
            projection: Array<String>?,
            selection: String?,
            selectionArgs: Array<String>?,
            sortOrder: String?
    ): Cursor? = null

    override fun update(
            uri: Uri,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(
            uri: Uri,
            selection: String?,
            selectionArgs: Array<String>?
    ): Int = 0

    override fun getType(uri: Uri): String? = null
}