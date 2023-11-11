package dev.linmaung.atantat.kmm.shared.cache

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}