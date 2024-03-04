package com.example.mywork122.data.localDbFactory

import com.example.mywork122.database.WorkDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(WorkDatabase.Schema,  "test.db")
    }
}




