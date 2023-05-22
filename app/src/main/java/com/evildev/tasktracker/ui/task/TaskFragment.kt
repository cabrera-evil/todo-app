package com.evildev.tasktracker.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.evildev.tasktracker.R
import com.evildev.tasktracker.databinding.FragmentTaskBinding
import com.evildev.tasktracker.ui.task.viewmodel.TaskViewModel

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private val taskViewModel: TaskViewModel by activityViewModels {
        TaskViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
    }

    private fun setViewModel() {
        binding.viewmodel = taskViewModel
    }
}