package com.example.mvvmrepo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrepo.data.model.AcronymItem
import com.example.mvvmrepo.databinding.ListItemAddOnBinding

class DictionaryAdapter :
    RecyclerView.Adapter<DictionaryAdapter.CustomViewHolder>() {
    private var dictionaryList: List<AcronymItem> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemAddOnBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(dictionary: List<AcronymItem>) {
        this.dictionaryList = dictionary
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dictionaryList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(dictionaryList[position])
    }

    class CustomViewHolder(private val binding: ListItemAddOnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: AcronymItem) {
            binding.data = data
        }
    }
}