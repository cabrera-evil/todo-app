package com.evildev.tasktracker.ui.task.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evildev.tasktracker.data.TaskModel
import com.evildev.tasktracker.databinding.FragmentTaskBinding
import com.evildev.tasktracker.databinding.TaskItemBinding

class TaskRecyclerViewAdapter(private val clickListener: (TaskModel) -> Unit) :
    RecyclerView.Adapter<TaskRecyclerViewHolder>() {
    private val tasks = ArrayList<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskRecyclerViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskRecyclerViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, clickListener)
    }

    fun setData(tasks: List<TaskModel>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
    }
}