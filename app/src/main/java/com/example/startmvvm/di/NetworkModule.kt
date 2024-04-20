package com.example.startmvvm.di

import android.content.Context
import com.example.startmvvm.data.datasource.MealAPI
import com.example.startmvvm.data.repository.MealRepoImpl
import com.example.startmvvm.domain.usecase.CategoryMealUseCase
import com.example.startmvvm.domain.usecase.RandomMealUseCase
import com.example.startmvvm.domain.usecase.SearchMealUseCase
import com.example.startmvvm.presentor.activities.CategoriesActivity
import com.example.startmvvm.utils.Constants.BASEURL
import com.example.startmvvm.utils.DialogsUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Provides
    @Singleton
    fun provideMealAPI():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    @Provides
    @Singleton
    fun provideMealAPIRepo(retrofit: Retrofit):MealAPI{
        return retrofit.create(MealAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomMealUseCase(mealAPI: MealAPI):RandomMealUseCase=RandomMealUseCase(mealAPI)

    @Provides
    @Singleton
    fun provideDialogsUtils()=DialogsUtils()

    @Provides
    @Singleton
    fun provideSearchMealUseCase(mealAPI: MealAPI):SearchMealUseCase{
        return SearchMealUseCase(mealAPI)
    }
    @Provides
    @Singleton
    fun provideCategoryMealUseCase(mealAPI: MealAPI):CategoryMealUseCase{
        return CategoryMealUseCase(mealAPI)
    }
    @Provides
    @Singleton
    fun provideCategoryActivityContext():Context=CategoriesActivity()
}