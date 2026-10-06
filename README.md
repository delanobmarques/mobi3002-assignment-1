# MOBI 3002 — Assignment 1: Task Manager

**Course:** MOBI 3002 Mobile Application Development  
**College:** Nova Scotia Community College

---

## Project Overview

This is a starter Android app built with Jetpack Compose. It is a simple Task Manager that displays a list of tasks, allows you to add new tasks, and delete existing ones.

The app compiles and runs — but it contains deliberate bugs that break the architecture patterns covered in class. Your job is to find them, fix them, and then add a new feature.

---

## Setup Instructions

### 1. Clone the repository

```bash
git clone <your-repo-url>
```

### 2. Open in Android Studio

- Open **Android Studio**
- Select **File → Open** and navigate to the cloned folder
- Wait for Gradle to sync

### 3. Verify dependencies

Make sure your `app/build.gradle.kts` includes the following dependencies:

```kotlin
implementation("androidx.lifecycle:lifecycle-runtime-compose")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
implementation("androidx.compose.material:material-icons-extended")
```

### 4. Run the app

- Connect a device or start an emulator
- Click **Run** (▶) in Android Studio
- The app should launch and display a list of sample tasks

---

## Project Structure

```
app/src/main/java/ca/nscc/taskmanager/
├── model/
│   └── Task.kt
├── viewmodel/
│   └── TaskViewModel.kt
├── ui/
│   ├── screens/
│   │   ├── TaskListScreen.kt
│   │   └── AddTaskScreen.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── MainActivity.kt
```

---

## Assignment Instructions

Full assignment details are available on **Brightspace**.

