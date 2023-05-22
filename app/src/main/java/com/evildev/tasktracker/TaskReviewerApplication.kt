package com.evildev.tasktracker

import android.app.Application
import com.evildev.tasktracker.data.tasks
import com.evildev.tasktracker.repositories.TaskRepository

class TaskReviewerApplication : Application() {
    val taskRepository: TaskRepository by lazy {
        TaskRepository(tasks)
    }
}