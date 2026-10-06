package ca.nscc.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import ca.nscc.taskmanager.model.Task
import ca.nscc.taskmanager.model.sampleTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Bug hint: Shared state — holds the current list of tasks
val _tasks = MutableStateFlow(sampleTasks)

class TaskViewModel : ViewModel() {

    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(
        title:    String,
        dueDate:  String,
        priority: String,
        notes:    String = ""
    ) {
        _tasks.update { current ->
            current + Task(
                id       = (current.maxOfOrNull { it.id } ?: 0) + 1,
                title    = title,
                dueDate  = dueDate,
                priority = priority,
                notes    = notes
            )
        }
    }

    fun deleteTask(id: Int) {
        _tasks.update { current -> current.filter { it.id != id } }
    }

}
