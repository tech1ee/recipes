package com.example.recipes.presentation.recipedetails.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.recipes.databinding.FragmentIngredientListBinding
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipedetails.overview.RecipeOverviewFragment

class RecipeIngredientsFragment: BaseFragment<FragmentIngredientListBinding>() {

    private val viewModel by viewModels<RecipeIngredientsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        arguments?.getLong(EXTRA_ID)?.let { id -> viewModel.setup(id) }
    }

    private fun observe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            progress(state.loading)

        }
    }

    private fun progress(inProgress: Boolean) {
        binding?.progressBar?.visibility = if (inProgress) View.VISIBLE else View.GONE
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientListBinding
        get() = FragmentIngredientListBinding::inflate


    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun newInstance(id: Long) = RecipeOverviewFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_ID, id)
            }
        }
    }
}