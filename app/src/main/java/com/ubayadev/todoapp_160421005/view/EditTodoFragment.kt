package com.ubayadev.todoapp_160421005.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubayadev.todoapp_160421005.R
import com.ubayadev.todoapp_160421005.databinding.FragmentCreateTodoBinding
import com.ubayadev.todoapp_160421005.databinding.FragmentEditTodoBinding
import com.ubayadev.todoapp_160421005.model.Todo
import com.ubayadev.todoapp_160421005.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener, SaveTodoClick {
    private lateinit var binding: FragmentEditTodoBinding
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTodoBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save Changes"

        binding.radioListener = this
        binding.saveListener = this

//        binding.btnAdd.setOnClickListener {
//            val radio =
//                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(binding.txtTitle.text.toString(),
//                binding.txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.todo = it
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)
//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                else -> binding.radioHigh.isChecked = true
//            }
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }
}