package com.example.startmvvm.data.datasource.dto

import com.example.startmvvm.domain.models.CategoriesModel
import com.example.startmvvm.domain.models.CategoryChildModel

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
){
    fun toCategory():CategoryChildModel{
        return CategoryChildModel(
            idCategory = idCategory,
            strCategory=strCategory,
            strCategoryDescription=strCategoryDescription,
            strCategoryThumb=strCategoryThumb,
        )
    }
}