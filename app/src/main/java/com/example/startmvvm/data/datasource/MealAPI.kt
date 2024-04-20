package com.example.startmvvm.data.datasource


import com.example.startmvvm.data.datasource.dto.CategoriesDTO
import com.example.startmvvm.data.datasource.dto.MealDTO
import com.example.startmvvm.data.datasource.dto.MealResponseDTO
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("/api/json/v1/1/random.php")
    suspend fun randomMealRequest(@Query("api_key") startWith:String): MealResponseDTO

    @GET("/api/json/v1/1/search.php")
    suspend fun searchMealRequest(@Query("f") searchLetter:String):MealResponseDTO

    @GET("/api/json/v1/1/categories.php")
    suspend fun SearchCategoriesMeal(@Query("f") searchLetter: String): CategoriesDTO
}