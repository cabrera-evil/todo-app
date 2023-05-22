package com.evildev.tasktracker.ui.task.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.evildev.tasktracker.R
import com.evildev.tasktracker.data.TaskModel
import com.evildev.tasktracker.databinding.FragmentHomeBinding
import com.evildev.tasktracker.ui.task.home.recyclerview.TaskRecyclerViewAdapter
import com.evildev.tasktracker.ui.task.viewmodel.TaskViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskRecyclerViewAdapter
    private val taskViewModel: TaskViewModel by activityViewModels {
        TaskViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showSelectedItem(task: TaskModel) {
        taskViewModel.setSelectedTask(task)
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_taskFragment)
    }

    private fun displayTasks() {
        adapter.setData(taskViewModel.getTasks())
        adapter.notifyDataSetChanged()
    }

    private fun setRecyclerView(view:View){
        adapter = TaskRecyclerViewAdapter(){
            selectTask -> showSelectedItem(selectTask)
        }

        binding.recyclerView.adapter = adapter
        displayTasks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(view)

        binding.addTaskFab.setOnClickListener{
            taskViewModel.clearData()
            it.findNavController().navigate(R.id.action_homeFragment_to_newTaskFragment)
        }
    }
}