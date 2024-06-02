package com.ubayadev.todoapp_160421005.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.todoapp_160421005.model.TodoDatabase

val DB_NAME = "newtododb"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

fun buildDb(context: Context): TodoDatabase {
    val db = Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME).build()
    return db
}