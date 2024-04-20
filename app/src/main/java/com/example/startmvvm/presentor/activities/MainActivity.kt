package com.example.startmvvm.presentor.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.startmvvm.R
import com.example.startmvvm.databinding.ActivityMainBinding
import com.example.startmvvm.presentor.viewmodel.RandomMealVM
import com.example.startmvvm.utils.DialogsUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var randomMealVM: RandomMealVM
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    lateinit var dbind:RandomMealVM

    @Inject
    lateinit var dialogsUtils: DialogsUtils
    var progressBar: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        try {
            randomMealVM = ViewModelProvider(this).get(RandomMealVM::class.java)

            randomMealVM.getAllMeals("")
            binding!!.randomMeal=randomMealVM
            progressBar = dialogsUtils.showProgressDialog(this@MainActivity, "Please Waiting...")
            CoroutineScope(Dispatchers.IO).launch {
                randomMealVM._mealvalue.collectLatest {
                    if (it.mealList.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            //binding!!.txtData.text=randomMealVM.resulttext.toString()
//                            binding!!.txtData.text = it.toString()
//                            // Log.d("TAGed", it.mealList.get(0).meals.get(0).strMealThumb)
                            Glide.with(this@MainActivity)
                                .load(it.mealList.get(0).meals.get(0).strMealThumb).fitCenter()
                                .placeholder(R.drawable.bibimbap)
                                .into(binding!!.imageView)
                            progressBar.let {
                                progressBar?.dismiss()
                            }
                        }

                    } else if (it.isLoading) {
                    } else if (it.error.isNotBlank()) {
                        progressBar.let {
                            progressBar?.dismiss()
                        }
                    }


                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



//    override fun onDestroy() {
//        super.onDestroy()
//        _binding=null
//    }
}