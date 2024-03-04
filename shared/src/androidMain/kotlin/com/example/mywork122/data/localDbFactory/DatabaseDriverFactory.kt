package com.example.mywork122.data.localDbFactory

import android.content.Context
import com.example.mywork122.database.WorkDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(WorkDatabase.Schema, context, "translate.db")
    }
}


