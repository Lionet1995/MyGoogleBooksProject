package com.example.mygooglebooksproject.presentation.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
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
import kotlinx.coroutines.launch
import javax.inject.Inject


class BooksListFragment : Fragment(), MenuProvider {
    @Inject
    lateinit var viewModelFactory: BooksListViewModelFactory
    private lateinit var viewModel: BooksListViewModel

    private var _binding: FragmentBooksListBinding? = null
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
    ): View {
        _binding = FragmentBooksListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[BooksListViewModel::class.java]

        val adapter = component.booksListAdapter

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        with(binding) {
            viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Success -> {

                        adapter.books = uiState.result
                        shimmerViewContainer.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }

                    is UiState.Loading -> {

                        emptyStateLottie.visibility = View.GONE
                        noResultsStateLottie.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        shimmerViewContainer.visibility = View.VISIBLE

                    }

                    is UiState.Empty -> {

                        shimmerViewContainer.visibility = View.GONE
                        emptyStateLottie.visibility = View.VISIBLE
                    }

                    is UiState.NoResults -> {

                        shimmerViewContainer.visibility = View.GONE
                        noResultsStateLottie.visibility = View.VISIBLE
                    }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            searchView.maxWidth = Int.MAX_VALUE
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