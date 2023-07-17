package com.example.todoappjetpackcompose.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappjetpackcompose.model.Todo
import com.example.todoappjetpackcompose.model.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository
): ViewModel() {

    val allTodos: LiveData<List<Todo>> = todoRepository.getAllTodo

    fun insert(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todo)
        }
    }

//    fun insert(title: String, desc: String, lastUpdate: String, isDone: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val todo = Todo(title = title, description = desc, lastUpdated = lastUpdate, isDone = isDone)
//            todoRepository.insert(todo)
//        }
//    }

    fun getTodoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getTodoById(id)
        }
    }

    fun delete(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todo)
        }
    }


    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}