package com.example.ledapps_demo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ledapps_demo.databinding.CategoryItemBinding

/**
 *@Created by Yerimah on 01/12/2021.
 */
class CategoryAdapter(private val categoryItems: List<Int>,
                      selectedItemIndex: Int,
                      private val callBack: (Int) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private var selectedPosition = selectedItemIndex

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(position)
    }

    override fun getItemCount(): Int = categoryItems.size

    inner class CategoryViewHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.imageView.clipToOutline = true
            binding.root.isSelected = selectedPosition == position

            binding.root.setOnClickListener {
                callBack.invoke(position)
                notifyItemChanged(selectedPosition)
                selectedPosition = layoutPosition
                notifyItemChanged(selectedPosition)
            }
            binding.imageView.setImageResource(categoryItems[position])

            if (selectedPosition == position) {
                binding.unselectedTaint.visibility = View.GONE
                binding.indicatorImageView.visibility = View.VISIBLE
            }
            else {
                binding.unselectedTaint.visibility = View.VISIBLE
                binding.indicatorImageView.visibility = View.GONE
            }
        }
    }
}