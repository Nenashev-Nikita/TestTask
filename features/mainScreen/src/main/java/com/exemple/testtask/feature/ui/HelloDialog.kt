package com.exemple.testtask.feature.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.testtask.features.mainScreen.R
import com.example.testtask.features.mainScreen.databinding.DialogHelloBinding
import com.exemple.testtask.feature.presentation.MainScreenState
import com.exemple.testtask.feature.presentation.MainScreenViewModel

class HelloDialog(private val viewModel: MainScreenViewModel) : DialogFragment() {
    companion object {
        fun newInstance(viewModel: MainScreenViewModel): HelloDialog =
            HelloDialog(viewModel)
    }

    private lateinit var binding: DialogHelloBinding
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireContext().let {
            val builder = AlertDialog.Builder(it)
            val currentState = viewModel.state.value as? MainScreenState.Content ?: throw IllegalStateException()
            binding = DialogHelloBinding.inflate(
                requireActivity().layoutInflater
            )
            builder.setView(binding.root)
            getString(R.string.user_greetings_text, )
            binding.dialogHello.text = binding.root.resources.getString(R.string.user_greetings_text, currentState.Name)
            builder.setPositiveButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }

            builder.create()
        }
    }
}