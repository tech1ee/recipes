package com.example.recipes.presentation.recipedetails.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentIngredientListBinding
import com.example.recipes.entity.Ingredient
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipedetails.RecipeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeIngredientsFragment: BaseFragment<FragmentIngredientListBinding>() {

    private val recipeDetailsViewModel by viewModels<RecipeDetailsViewModel>(
        ownerProducer = { requireParentFragment() }
    )
    private val recipeIngredientsViewModel by viewModels<RecipeIngredientsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        recipeDetailsViewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            state.recipeId?.let { id ->
                recipeIngredientsViewModel.setup(id)
            }
        }
        recipeIngredientsViewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            progress(state.loading)
            if (!state.data.isNullOrEmpty()) handleIngredients(state.data)
            if (state.errorMessage != null) handleError(state.errorMessage)
        }
    }

    private fun progress(inProgress: Boolean) {
        binding?.progressBar?.visibility = if (inProgress) View.VISIBLE else View.GONE
    }

    private fun handleIngredients(list: List<Ingredient>) {
        binding?.ingredientList?.layoutManager = LinearLayoutManager(requireContext())
        binding?.ingredientList?.adapter = RecipeIngredientsAdapter().apply { setList(list) }
    }

    private fun handleError(message: String) {
        showAlert(
            title = getString(R.string.error),
            message = message
        ) {  }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientListBinding
        get() = FragmentIngredientListBinding::inflate
}