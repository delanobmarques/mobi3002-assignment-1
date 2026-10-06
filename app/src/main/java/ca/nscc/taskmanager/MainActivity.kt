package ca.nscc.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.nscc.taskmanager.ui.screens.AddTaskScreen
import ca.nscc.taskmanager.ui.screens.TaskListScreen
import ca.nscc.taskmanager.ui.theme.TaskManagerTheme
import ca.nscc.taskmanager.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                val taskViewModel: TaskViewModel = viewModel()
                var showAddScreen by remember { mutableStateOf(false) }

                if (showAddScreen) {
                    val tasks by taskViewModel.tasks.collectAsStateWithLifecycle()

                    AddTaskScreen(
                        viewModel = taskViewModel
                    )
                } else {
                    TaskListScreen(
                        viewModel  = taskViewModel,
                        onAddClick = { showAddScreen = true }
                    )
                }
            }
        }
    }
}
