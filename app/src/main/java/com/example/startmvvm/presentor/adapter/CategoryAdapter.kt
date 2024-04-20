package com.example.startmvvm.presentor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startmvvm.R
import com.example.startmvvm.data.datasource.dto.Category
import com.example.startmvvm.databinding.ItemCategoriesViewBinding
import com.example.startmvvm.domain.models.CategoriesModel
import com.example.startmvvm.presentor.activities.CategoriesActivity
import javax.inject.Inject

class CategoryAdapter(
    private val paymentList: List<Category>, private val context: Context ) : RecyclerView.Adapter<CategoryAdapter.PaymentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val itemBinding = ItemCategoriesViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentHolder(itemBinding,context)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val paymentBean: Category = paymentList[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = paymentList.size

    class PaymentHolder(private val itemBinding: ItemCategoriesViewBinding,private val context: Context) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(paymentBean: Category) {
            itemBinding.txtTitle.text = (position+1).toString() +": "+paymentBean.strCategory
            itemBinding.txtDesc.text = paymentBean.strCategoryDescription
            Glide.with(context)
                .load(paymentBean.strCategoryThumb)
                .placeholder(R.drawable.bibimbap)
                .into(itemBinding.imageView3);
        }
    }
}