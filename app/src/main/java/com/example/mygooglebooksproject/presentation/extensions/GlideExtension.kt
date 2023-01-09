package com.example.mygooglebooksproject.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}