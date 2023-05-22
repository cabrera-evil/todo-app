package com.evildev.tasktracker.ui.task.newtask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.evildev.tasktracker.R
import com.evildev.tasktracker.databinding.FragmentNewTaskBinding
import com.evildev.tasktracker.ui.task.viewmodel.TaskViewModel

class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding

    private val taskViewModel: TaskViewModel by activityViewModels {
        TaskViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setObserver()
    }

    private fun setViewModel() {
        binding.viewmodel = taskViewModel
    }

    private fun setObserver(){
        taskViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(TaskViewModel.WRONG_INFORMATION) -> {
                    Log.d("APP_TAG", status)
                    taskViewModel.clearStatus()
                }
                status.equals(TaskViewModel.TASK_CREATED) -> {
                    Log.d("APP_TAG", status)
                    Log.d("APP_TAG", taskViewModel.getTasks().toString())

                    taskViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }
}