package com.example.mygooglebooksproject.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mygooglebooksproject.R
import com.example.mygooglebooksproject.data.utils.Configs
import com.example.mygooglebooksproject.databinding.FragmentSettingsBinding
import com.example.mygooglebooksproject.presentation.app.appComponent
import com.example.mygooglebooksproject.presentation.viewmodels.SettingsFragmentViewModel
import com.example.mygooglebooksproject.presentation.viewmodels.SettingsFragmentViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: SettingsFragmentViewModelFactory
    private lateinit var viewModel: SettingsFragmentViewModel

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val component by lazy { requireContext().applicationContext.appComponent }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[SettingsFragmentViewModel::class.java]

        with(binding) {
            saveButton.setOnClickListener {
                val entry = editText.text.toString()
                if (entry.isNotEmpty()) {
                    if (entry.toInt() in  MIN_VALUE..Configs.MAX_RESULTS_VALUE.toInt()) {
                        viewModel.updateBooksCount(editText.text.toString().toInt())
                    } else {
                        Snackbar.make(root,
                            getString(R.string.invalid_value_text),
                            Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    Snackbar.make(root, getString(R.string.emptyFieldText), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.isDataUpdated = { findNavController().popBackStack() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MIN_VALUE = 1
    }
}