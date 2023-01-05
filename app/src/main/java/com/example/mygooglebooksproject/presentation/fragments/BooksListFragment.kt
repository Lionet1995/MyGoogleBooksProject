package com.example.mygooglebooksproject.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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

class BooksListFragment : Fragment(), MenuProvider {
    @Inject
    lateinit var viewModelFactory: BooksListViewModelFactory
    private lateinit var viewModel: BooksListViewModel

    private val adapter = BooksListAdapter()

    private val component by lazy { requireContext().applicationContext.appComponent }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBooksListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[BooksListViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.booksList.observe(viewLifecycleOwner) {
            adapter.books = it
        }

        binding.settingsButton.setOnClickListener {
            goToSettings()
        }

        parentFragmentManager.setFragmentResultListener(SettingsFragment.REQUEST_CODE,
            viewLifecycleOwner) { _, data ->
            data.getString(SettingsFragment.COUNT_VALUE)?.let {
                adapter.itemsCount = it.toInt()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireHost() as MenuHost
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView

        view?.width?.let {
            searchView.maxWidth = it
        }

        searchView.queryHint = getString(R.string.search_books)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ITEMS_COUNT_KEY, adapter.itemsCount)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
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