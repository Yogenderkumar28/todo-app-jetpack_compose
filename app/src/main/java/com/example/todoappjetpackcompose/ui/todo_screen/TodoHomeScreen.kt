package com.example.todoappjetpackcompose.ui.todo_screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.todoappjetpackcompose.view_model.TodoViewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.todoappjetpackcompose.model.Todo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreen(
    context: Context,
    viewModel: TodoViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val todos = viewModel.allTodos
    val todoList by todos.observeAsState(emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_edit_screen")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {contentPadding->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            items(todoList){ todo->
//                context?.let {
//                    viewModel.showToast(it, "todoId: ${todo.id}, todoTitle: ${todo.title}, tododescription: ${todo.description}")
//                }
                TodoItemScreen(
                    todo = todo,
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("ADD_EDIT_TODO + ?todoId=${todo.id}")
                        }
                )
            }
        }

    }
}