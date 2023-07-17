package com.example.todoappjetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    val title: String,
    val description: String,
    var isDone: Boolean,
    val lastUpdated: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) {
   fun toggleIsDone(isDone: Boolean) {
        this.isDone = isDone
   }
}
