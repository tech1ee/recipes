package com.example.recipes.presentation.recipelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.databinding.ItemRecipeListBinding
import com.example.recipes.entity.RecipeInformation

class RecipesAdapter: RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val list = mutableListOf<RecipeInformation>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<RecipeInformation>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeListBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(binding: ItemRecipeListBinding): RecyclerView.ViewHolder(binding.root) {

        private val image = binding.image
        private val title = binding.title
        private val summary = binding.summary

        fun bind(position: Int) {
            val item = list[position]

            Glide.with(image).load(item.image).submit()

            title.text = item.title
            summary.text = item.summary
        }
    }
}