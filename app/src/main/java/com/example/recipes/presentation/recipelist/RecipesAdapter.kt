package com.example.recipes.presentation.recipelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.entity.RecipeInformation

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val list = mutableListOf<RecipeInformation>()

    var itemClickListener: ((recipe: RecipeInformation) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<RecipeInformation>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

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
            item.summary?.let { txt ->
                summary.text = HtmlCompat.fromHtml(txt, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }

            itemView.setOnClickListener { itemClickListener?.invoke(item) }
        }
    }
}