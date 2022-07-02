package com.example.recipes.presentation.recipedetails.ingredients

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.entity.Ingredient

class RecipeIngredientsAdapter: RecyclerView.Adapter<RecipeIngredientsAdapter.ViewHolder>() {

    private val list = mutableListOf<Ingredient>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Ingredient>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val image: ImageView? = view.findViewById(R.id.image)
        private val text: TextView? = view.findViewById(R.id.text)

        fun bind(position: Int) {
            val item = list[position]

            image?.let { img ->
                Glide.with(itemView.context).load(item.image).into(img)
            }
            text?.text = "${item.name} ${item.amount?.metric?.value?.toInt()} ${item.amount?.metric?.unit}"
        }
    }
}