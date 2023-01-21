package com.example.mygooglebooksproject.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygooglebooksproject.R
import com.example.mygooglebooksproject.databinding.FragmentBooksListBinding
import com.example.mygooglebooksproject.presentation.app.appComponent
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModel
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModelFactory
import com.example.mygooglebooksproject.presentation.viewmodels.UiState
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksListFragment : Fragment(), MenuProvider {
    @Inject
    lateinit var viewModelFactory: BooksListViewModelFactory
    private lateinit var viewModel: BooksListViewModel

    private val component by lazy { requireContext().applicationContext.appComponent }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentBooksListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[BooksListViewModel::class.java]

        val adapter = component.booksListAdapter

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    progressLayout.visibility = View.GONE
                    adapter.books = uiState.result
//                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }

                is UiState.Loading -> {
                    progressLayout.visibility = View.VISIBLE
//                    requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

                is UiState.Error -> {
                    progressLayout.visibility = View.GONE
//                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(requireContext(), uiState.error, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        lifecycle.coroutineScope.launch {
            viewModel.getBooksCount().collect {
                adapter.itemsCount = it
            }
        }

        binding.settingsButton.setOnClickListener {
            goToSettings()
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
                    viewModel.getBooksList(query)
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

    private fun goToSettings() {
        findNavController().navigate(R.id.action_booksListFragment_to_settingsFragment)
    }
}