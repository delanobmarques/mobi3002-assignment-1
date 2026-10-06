package ca.nscc.taskmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.nscc.taskmanager.model.Task
import ca.nscc.taskmanager.model.sampleTasks
import ca.nscc.taskmanager.ui.theme.CoralRed
import ca.nscc.taskmanager.ui.theme.WarmYellow
import ca.nscc.taskmanager.ui.theme.ForestGreen
import ca.nscc.taskmanager.ui.theme.TaskManagerTheme
import ca.nscc.taskmanager.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel:  TaskViewModel = viewModel(),
    onAddClick: () -> Unit    = {}
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "My Tasks",
                        style      = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick        = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor   = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add task")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SummaryBar(total = tasks.size, pending = tasks.size) // Bug hint: pending count is wrong

            LazyColumn(
                contentPadding      = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = tasks, key = { it.id }) { task ->

                    TaskItem(task = task)   // ← Bug hint: onDelete not wired
                }
            }
        }
    }
}

@Composable
fun SummaryBar(total: Int, pending: Int) {
    Surface(
        color = MaterialTheme.colorScheme.primary  // ← was primaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text(
                text  = "$total task${if (total == 1) "" else "s"}",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.80f)
            )
            Text(
                text       = "$pending pending",
                style      = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
        }
    }
}

@Composable
fun TaskItem(
    task:     Task,
    onDelete: () -> Unit = {}
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = task.title,
                    style      = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text  = "Due: ${task.dueDate}  ·  ${task.priority}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                if (task.notes.isNotBlank()) {
                    Text(
                        text  = task.notes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                PriorityBadge(priority = task.priority)
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector        = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint               = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun PriorityBadge(priority: String) {
    val (bg, fg) = when (priority) {
        "High"   -> Pair(Color(0xFFFFEBEB), CoralRed)
        "Medium" -> Pair(Color(0xFFFFFAEB), WarmYellow)
        else     -> Pair(Color(0xFFEBF9F1), ForestGreen)
    }
    Surface(
        color  = bg,
        shape  = RoundedCornerShape(6.dp)
    ) {
        Text(
            text      = priority,
            style     = MaterialTheme.typography.labelSmall,
            color     = fg,
            fontWeight = FontWeight.Bold,
            modifier  = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskManagerTheme { TaskListScreen() }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskManagerTheme { TaskItem(task = sampleTasks.first()) }
}
