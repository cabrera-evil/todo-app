package com.evildev.tasktracker.ui.task.viewmodel

import android.text.Spannable.Factory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evildev.tasktracker.TaskReviewerApplication
import com.evildev.tasktracker.data.TaskModel
import com.evildev.tasktracker.repositories.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    //    LiveData values
    var description = MutableLiveData("")
    var status = MutableLiveData("")

    //    Repository methods
    fun getTasks() = taskRepository.getTasks()
    fun addTask(task: TaskModel) = taskRepository.addTask(task)

    //    Creating task
    fun createTask() {
        if (!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        val task = TaskModel(
            description.value!!,
            status.value!!
        )

        addTask(task)
        status.value = TASK_CREATED
    }

    //    Validate data fields
    private fun validateData(): Boolean {
        Log.d("TaskViewModel", "validateData: ${description.value} ${status.value}")
        when {
            description.value.isNullOrEmpty() -> return false
            status.value.isNullOrEmpty() -> return false
        }
        return true
    }

    //    Clearing data
    fun clearData() {
        description.value = ""
        status.value = ""
    }

    fun clearStatus() {
        status.value = INACTIVE
    }

    //    setSelectedTask
    fun setSelectedTask(task: TaskModel) {
        description.value = task.description
        status.value = task.status
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as TaskReviewerApplication
                TaskViewModel(app.taskRepository)
            }
        }
        const val TASK_CREATED = "Task created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = "Inactive"
    }
}