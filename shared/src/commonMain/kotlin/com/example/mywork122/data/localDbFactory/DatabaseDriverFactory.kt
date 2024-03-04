package com.example.mywork122.data.localDbFactory

import com.squareup.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}











