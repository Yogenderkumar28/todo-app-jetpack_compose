package com.example.todoappjetpackcompose.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoappjetpackcompose.model.TodoRepository

class TodoViewModelFactory(
    private val todoRepository: TodoRepository
) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(todoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}