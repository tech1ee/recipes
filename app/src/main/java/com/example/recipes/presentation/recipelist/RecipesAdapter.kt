package com.example.recipes.presentation.recipelist

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.entity.RecipeInformation

class RecipesAdapter(private val list: List<RecipeInformation>) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesAdapter.ViewHolder, position: Int) =
        holder.bind(position)

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.findViewById<ImageView?>(R.id.image)
        private val title = view.findViewById<TextView?>(R.id.title)
        private val summary = view.findViewById<TextView?>(R.id.summary)

        fun bind(position: Int) {
            val item = list[position]

            Glide.with(image).load(item.image).into(image)

            title.text = item.title
            summary.text = item.summary
        }
    }
}