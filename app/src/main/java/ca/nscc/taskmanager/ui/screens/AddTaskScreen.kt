package ca.nscc.taskmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.nscc.taskmanager.ui.theme.TaskManagerTheme
import ca.nscc.taskmanager.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel:   TaskViewModel = viewModel(),
    onBackClick: () -> Unit    = {}
) {
    var title            by remember { mutableStateOf("") }
    var dueDate          by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }
    var notes            by remember { mutableStateOf("") }

    val priorities = listOf("High", "Medium", "Low")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Add Task",
                        style      = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value         = title,
                onValueChange = { title = it },
                label         = { Text("Title") },
                modifier      = Modifier.fillMaxWidth(),
                singleLine    = true
            )

            OutlinedTextField(
                value         = dueDate,
                onValueChange = { dueDate = it },
                label         = { Text("Due Date (YYYY-MM-DD)") },
                modifier      = Modifier.fillMaxWidth(),
                singleLine    = true
            )

            OutlinedTextField(
                value         = notes,
                onValueChange = { notes = it },
                label         = { Text("Notes (optional)") },
                modifier      = Modifier.fillMaxWidth(),
                maxLines      = 3
            )

            Text(text = "Priority", style = MaterialTheme.typography.labelLarge)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                priorities.forEach { priority ->
                    FilterChip(
                        selected = selectedPriority == priority,
                        onClick  = { selectedPriority = priority },
                        label    = { Text(priority) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick  = {
                    viewModel.addTask(title, dueDate, selectedPriority, notes)
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                enabled  = title.isNotBlank() && dueDate.isNotBlank()
            ) {
                Text("Save Task", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    TaskManagerTheme { AddTaskScreen() }
}
