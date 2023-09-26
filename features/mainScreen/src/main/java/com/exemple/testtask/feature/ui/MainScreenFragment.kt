package com.exemple.testtask.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtask.features.mainScreen.databinding.MainscreenFragmentBinding
import com.exemple.testtask.feature.presentation.MainScreenState
import com.exemple.testtask.feature.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {
    companion object {
        private const val DIALOG_Hello = "NameDialog"

        fun newInstance(): MainScreenFragment =
            MainScreenFragment()
    }

    private lateinit var binding: MainscreenFragmentBinding
    private val viewModel: MainScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainscreenFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListener()
        viewModel.getUserName()
    }

    private fun setListener() {
        with(binding) {
            button.setOnClickListener {
                HelloDialog.newInstance(viewModel).apply {
                    show(this@MainScreenFragment.parentFragmentManager, DIALOG_Hello)
                }
            }
            toolbar.setOnClickListener {
                viewModel.getRegistration()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: MainScreenState) {
        when (state) {
            is MainScreenState.Content -> renderContent(state)
            else -> renderLoading()
        }
    }

    private fun renderContent(state: MainScreenState.Content) {
        with(binding) {
            progressBar.visibility = View.GONE
            button.visibility = View.VISIBLE
            //textView.text = state.Name
        }
    }

    private fun renderLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            button.visibility = View.GONE
        }
    }
}