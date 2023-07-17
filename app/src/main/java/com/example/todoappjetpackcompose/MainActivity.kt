package com.example.todoappjetpackcompose

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoappjetpackcompose.model.TodoDatabase
import com.example.todoappjetpackcompose.model.TodoRepository
import com.example.todoappjetpackcompose.ui.add_edit_todo.AddEditScreen
import com.example.todoappjetpackcompose.ui.splash_screen.SplashScreen
import com.example.todoappjetpackcompose.ui.theme.TodoAppJetPackComposeTheme
import com.example.todoappjetpackcompose.ui.todo_screen.TodoHomeScreen
import com.example.todoappjetpackcompose.view_model.TodoViewModel
import com.example.todoappjetpackcompose.view_model.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var database: TodoDatabase
    private lateinit var todoRepository: TodoRepository
    private lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = TodoDatabase.getInstance(this)
        todoRepository = TodoRepository(database.todoDao())
        todoViewModel = ViewModelProvider(this, TodoViewModelFactory(todoRepository)).get(TodoViewModel::class.java)
        setContent {
            TodoAppJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen",
                ) {
                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }

                    composable("todo_list") {
                        TodoHomeScreen(
                            context = this@MainActivity,
                            viewModel = todoViewModel,
                            navController = navController
                        )
                    }

                    composable(
                        route = "add_edit_screen" + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument("todoId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ){
                        val todoId = it.arguments?.getInt("todoId") ?: -1

                        AddEditScreen(
                            viewModel = todoViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

