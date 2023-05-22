package com.evildev.tasktracker.repositories

import com.evildev.tasktracker.data.TaskModel

class TaskRepository(private val tasks: MutableList<TaskModel>) {
    fun getTasks() = tasks

    fun addTask(taskModel: TaskModel) {
        tasks.add(taskModel)
    }
}