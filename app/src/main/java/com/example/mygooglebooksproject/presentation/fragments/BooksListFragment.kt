package com.example.mygooglebooksproject.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygooglebooksproject.R
import com.example.mygooglebooksproject.databinding.FragmentBooksListBinding
import com.example.mygooglebooksproject.presentation.adapters.BooksListAdapter
import com.example.mygooglebooksproject.presentation.app.appComponent
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModel
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class BooksListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: BooksListViewModelFactory
    private lateinit var viewModel: BooksListViewModel

    lateinit var adapter: BooksListAdapter

    private val component by lazy { requireContext().applicationContext.appComponent }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding: FragmentBooksListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_books_list,
            container,
            false
        )

        viewModel = ViewModelProvider(this, viewModelFactory)[BooksListViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        adapter = component.booksListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.booksList.observe(viewLifecycleOwner) { //обзервим изменения в лайвдате и отдаем измененный список адаптеру
            adapter.books = it
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchBooksByEntry(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        binding.settingsButton.setOnClickListener { //слушаем нажатие на кнопку настройки и переходим на фрагмент настроек
            goToSettings()
        }

        parentFragmentManager.setFragmentResultListener(SettingsFragment.REQUEST_CODE, // возвращаем с фрагмента настроек новое число айтемов для ресайклера и обновляем
            viewLifecycleOwner) { _, data ->
            data.getString(SettingsFragment.COUNT_VALUE)?.let {
                adapter.itemsCount = it.toInt()
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ITEMS_COUNT_KEY, adapter.itemsCount)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState!=null) {
            adapter.itemsCount = savedInstanceState.getInt(ITEMS_COUNT_KEY);
        }
    }

    private fun goToSettings() {
        findNavController().navigate(R.id.action_booksListFragment_to_settingsFragment)
    }

    companion object {
        const val ITEMS_COUNT_KEY = "countKey"
    }
}