package ca.nscc.taskmanager.model

// Data model
data class Task(
    val id:       Int,
    val title:    String,
    val dueDate:  String,
    val priority: String,   // "High", "Medium", "Low"
    val notes:    String = ""
)

val sampleTasks = listOf(
    Task(1, "Submit assignment",    "2026-10-13", "High",   notes = "Upload to BrightSpace"),
    Task(2, "Read chapter 5",       "2026-10-10", "Medium"),
    Task(3, "Buy groceries",        "2026-10-07", "Low"),
    Task(4, "Book dentist appt",    "2026-10-20", "Medium", notes = "Call before noon"),
    Task(5, "Review pull request",  "2026-10-08", "High"),
    Task(6, "Clean up repo",        "2026-10-15", "Low",   notes = "Remove unused branches"),
)
