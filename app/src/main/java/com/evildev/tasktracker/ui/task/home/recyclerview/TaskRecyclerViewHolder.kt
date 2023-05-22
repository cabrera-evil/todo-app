package com.evildev.tasktracker.ui.task.home.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.evildev.tasktracker.data.TaskModel
import com.evildev.tasktracker.databinding.FragmentTaskBinding
import com.evildev.tasktracker.databinding.TaskItemBinding

class TaskRecyclerViewHolder(private val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(task: TaskModel, clickListener: (TaskModel) -> Unit){
        binding.descriptionTextView.text = task.description
        binding.statusTextView.text = task.status

        binding.taskItemCardView.setOnClickListener {
            clickListener(task)
        }
    }
}