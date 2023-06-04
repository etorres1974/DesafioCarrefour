package com.example.desafiocarrefour.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.desafiocarrefour.R
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(url : String){
    Picasso.get()
        .load(url)
        .fit()
        .error(R.drawable.ic_baseline_error_outline_24_material_red)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun TextView.addTextOrHide(text : String?){
    this.text = text
    isVisible = !text.isNullOrEmpty()
}