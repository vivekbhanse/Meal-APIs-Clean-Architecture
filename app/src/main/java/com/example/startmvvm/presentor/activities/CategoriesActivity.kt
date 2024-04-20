package com.example.startmvvm.presentor.activities

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startmvvm.R
import com.example.startmvvm.databinding.ActivityCategoriesBinding
import com.example.startmvvm.presentor.adapter.CategoryAdapter
import com.example.startmvvm.presentor.viewmodel.RandomMealVM
import com.example.startmvvm.utils.DialogsUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {
    private var binding: ActivityCategoriesBinding? = null
    private val _binding get() = binding
    var progressBar: ProgressDialog? = null
    @Inject
    lateinit var dialogsUtils: DialogsUtils
    lateinit var randomMealVM: RandomMealVM
    lateinit var ctx: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories)
        ctx=this
        randomMealVM = ViewModelProvider(this).get(RandomMealVM::class.java)
        randomMealVM.getCategoryMeal("")
        progressBar=dialogsUtils.showProgressDialog(this@CategoriesActivity, "Please Waiting...")
        CoroutineScope(Dispatchers.IO).launch {
            randomMealVM._mealvaluecat.collect {
                if (!it.mealList.isEmpty()) {
                    withContext(Dispatchers.Main) {
                       // _binding!!.txtData.text = it.mealList.get(0).categories.toString()
                        val adapter = CategoryAdapter(it.mealList.get(0).categories,ctx)
                        _binding!!.recCategory.adapter = adapter
                        _binding!!.recCategory.layoutManager = LinearLayoutManager(this@CategoriesActivity)

                    }
                    progressBar!!.dismiss()

                } else if (it.isLoading) {
                } else if (it.error.isNotBlank()) {
                    progressBar!!.dismiss()
                }
            }

        }

    }
}