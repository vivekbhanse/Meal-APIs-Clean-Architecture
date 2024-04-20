package com.example.startmvvm.presentor.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


class CustomSetter {
    companion object {
        @BindingAdapter("bind:imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}