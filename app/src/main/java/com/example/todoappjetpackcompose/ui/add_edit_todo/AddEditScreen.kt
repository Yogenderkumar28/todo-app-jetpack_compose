package com.example.todoappjetpackcompose.ui.add_edit_todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoappjetpackcompose.R
import com.example.todoappjetpackcompose.model.Todo
import com.example.todoappjetpackcompose.view_model.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: TodoViewModel,
    navController: NavController
) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val lastUpdate = remember { mutableStateOf("") }
    val isDone = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState()}
    Scaffold(
        snackbarHost =  { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(end = 20.dp, bottom = 30.dp),
                onClick = {
//                    context?.let{
////                        viewModel.showToast(it, "title: ${title.value}, Description: ${description.value}, Last Update: ${lastUpdate.value}")
//                    }
                    viewModel.insert(Todo(
                        title = title.value,
                        description = description.value,
                        lastUpdated = lastUpdate.value,
                        isDone = isDone.value
                    ))
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save_as),
                    contentDescription = "save_todo"
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            val currentDate = getDate()
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Last Updated: $currentDate",
                onValueChange = { lastUpdate.value = currentDate }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = title.value,
                onValueChange = { title.value = it },
                label = { Text(text = "Title")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),

            )
        }
    }
}

fun getDate(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("dd-MMMM-yyyy, hh-mm", Locale.getDefault())
    return dateFormat.format(currentDate)
}

@Preview
@Composable
fun PreviewAddEditTodoScreen() {
    MaterialTheme {
//        AddEditScreen()
    }
}