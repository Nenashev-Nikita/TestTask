package com.exemple.testtask.feature.registration.ui

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.testtask.features.registration.databinding.FragmentRegistrationBinding
import com.exemple.testtask.feature.registration.presentation.RegistrationState
import com.exemple.testtask.feature.registration.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.icu.text.DateFormat
import com.example.testtask.features.registration.R
import com.exemple.testtask.feature.registration.presentation.RegistrationErrorState
import java.util.Date

class RegistrationFragment : Fragment() {
    companion object {
        private const val DIALOG_DATE = "DateDialog"

        fun newInstance(): RegistrationFragment =
            RegistrationFragment()
    }

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(
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
    }


    private fun setListener() {

        with(binding) {
            nameEditText.doOnTextChanged { inputText, _, _, _ ->
                viewModel.checkName(inputText.toString())
            }
            surnameEditText.doOnTextChanged { inputText, _, _, _ ->
                viewModel.checkSurname(inputText.toString())
            }

            dateOfBirthEditText.setOnClickListener() {
                DateDialog.newInstance().apply {
                    this@RegistrationFragment.parentFragmentManager
                        .setFragmentResultListener(
                            DateDialog.REQUEST_DATE,
                            this
                        ) { requestKey, bundle ->
                            if (requestKey == DateDialog.REQUEST_DATE) {
                                val date: Date =
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        bundle.getSerializable(
                                            DateDialog.BUNDLE_DATE,
                                            Date::class.java
                                        ) as Date
                                    } else {
                                        bundle.getSerializable(DateDialog.BUNDLE_DATE) as Date
                                    }

                                val str = DateFormat.getPatternInstance("dd.MM.yyyy").format(date)
                                dateOfBirthEditText.setText(str)
                                viewModel.checkData(data = date)
                            }
                        }
                    show(
                        this@RegistrationFragment.parentFragmentManager,
                        DIALOG_DATE
                    )
                }
            }

            passwordEditText.doOnTextChanged { inputText, _, _, _ ->
                viewModel.checkPassword(inputText.toString())
            }
            confirmPasswordEditText.doOnTextChanged { inputText, _, _, _ ->
                viewModel.checkConfirmPassword(inputText.toString())
            }
            goButton.setOnClickListener {
                viewModel.broadcast()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        Log.d(TAG, "onSetObservers()")
    }

    private fun handleState(state: RegistrationState) {
        when (state) {
            is RegistrationState.Content -> renderContent(state)
            else -> renderLoading()
        }
        Log.d(TAG, "handleState()")
    }

    private fun renderContent(state: RegistrationState.Content) {
        with(binding) {
            progressBar.visibility = View.GONE
            screen.visibility = View.VISIBLE

            when (state.error) {
                RegistrationErrorState.NotError -> {
                    nameInputLayout.error = null
                }

                RegistrationErrorState.Name -> {
                    nameInputLayout.error = resources.getString(R.string.error_name)
                }

                RegistrationErrorState.Surname -> {
                    surnameInputLayout.error = resources.getString(R.string.error_surname)
                }

                RegistrationErrorState.Data -> {
                    dateOfBirthInputLayout.error = resources.getString(R.string.error_data)
                }

                RegistrationErrorState.Password -> {
                    passwordInputLayout.error = resources.getString(R.string.error_password)
                }

                RegistrationErrorState.ConfirmPassword -> {
                    confirmPasswordInputLayout.error =
                        resources.getString(R.string.error_confirm_password)
                }

                RegistrationErrorState.NotErrorName -> {
                    nameInputLayout.error = null
                }

                RegistrationErrorState.NotErrorSurname -> {
                    surnameInputLayout.error = null
                }

                RegistrationErrorState.NotErrorData -> {
                    dateOfBirthInputLayout.error = null
                }

                RegistrationErrorState.NotErrorPassword -> {
                    passwordInputLayout.error = null
                }

                RegistrationErrorState.NotErrorConfirmPassword -> {
                    confirmPasswordInputLayout.error = null
                }
            }
            goButton.isEnabled = viewModel.onCheck()
        }
        Log.d(TAG, "renderContent()")
    }

    private fun renderLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            screen.visibility = View.GONE
        }
        Log.d(TAG, "renderLoading()")
    }

}