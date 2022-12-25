package com.example.mygooglebooksproject.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mygooglebooksproject.R
import com.example.mygooglebooksproject.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentSettingsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        )

        with(binding) {
            saveButton.setOnClickListener {
                if (editText.text.toString().isNotEmpty()) {
                    parentFragmentManager.setFragmentResult(REQUEST_CODE,
                        bundleOf(COUNT_VALUE to editText.text.toString()))
                    findNavController().popBackStack()
                } else {
                    val snackbar =
                        Snackbar.make(root, "This field can't be empty", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }
        return binding.root
    }


    companion object {
        const val COUNT_VALUE = "COUNT"
        const val REQUEST_CODE = "COUNT_REQUEST_CODE"
    }
}