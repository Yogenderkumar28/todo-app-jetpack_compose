package com.example.todoappjetpackcompose.model

import androidx.lifecycle.LiveData

class TodoRepository(
    private val todoDao: TodoDao
) {

    val getAllTodo: LiveData<List<Todo>> = todoDao.getAllTodo()

    suspend fun insert(todo: Todo) {
        todoDao.insertTodo(todo)
    }


    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    suspend fun getTodoById(todoId: Long) {
        todoDao.getTodoById(todoId)
    }
}