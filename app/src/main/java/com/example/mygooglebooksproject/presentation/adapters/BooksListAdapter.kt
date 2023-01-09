package com.example.mygooglebooksproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygooglebooksproject.databinding.BookItemBinding
import com.example.mygooglebooksproject.domain.models.Book
import com.example.mygooglebooksproject.presentation.extensions.loadImageFromUrl

class BooksListAdapter(
) : RecyclerView.Adapter<BooksViewHolder>(){

    var itemsCount = DEFAULT_COUNT
    @SuppressLint("NotifyDataSetChanged")
    set(newValue) {
        field = newValue
        notifyDataSetChanged()
    }

    var books: List<Book> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(inflater, parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {

        with(holder.binding) {
            bookImage.loadImageFromUrl(books[position].source ?: DEFAULT_IMAGE)
            bookTitle.text = books[position].title
            bookDescription.text = books[position].description
        }
    }

    override fun getItemCount(): Int {
        return if(itemsCount < books.size) itemsCount else books.size
    }

    companion object {
        const val DEFAULT_COUNT = 4
        const val DEFAULT_IMAGE = "https://w7.pngwing.com/pngs/4/84/png-transparent-paperback-book-cover-auction-rectangle-creative-market-wood.png"
    }
}

class BooksViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)