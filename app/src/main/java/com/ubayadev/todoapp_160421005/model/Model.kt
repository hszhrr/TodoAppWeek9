package com.ubayadev.todoapp_160421005.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="notes")
    var notes: String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name = "is_done", defaultValue = "0")
    var isDone: Int = 0
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}