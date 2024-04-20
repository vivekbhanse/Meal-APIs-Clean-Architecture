package com.example.startmvvm.utils

import android.net.Uri

import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.startmvvm.R


class DataBindingAdapters {
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: String?) {
        imageUri.let {
            Glide.with(view.context)
                .load(imageUri)
                .placeholder(R.drawable.bibimbap)
                .into(view);
        }

    }

    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

}