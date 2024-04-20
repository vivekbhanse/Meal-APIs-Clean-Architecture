package com.example.startmvvm.domain.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.startmvvm.R


data class Meal(
    val dateModified: Any,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strMeal: String,
    val strMealThumb: String,

)

