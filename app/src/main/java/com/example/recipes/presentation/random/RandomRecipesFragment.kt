package com.example.recipes.presentation.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.FragmentRandomRecipesBinding
import com.example.recipes.entity.RecipeInformation
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipelist.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    private val viewModel by viewModels<RandomRecipesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.getRandomRecipes()
    }

    private fun observe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            when {
                state.loading -> Unit
                state.recipes != null -> handleRecipeList(state.recipes)
                !state.errorMessage.isNullOrEmpty() -> Unit
            }
        }
    }

    private fun handleRecipeList(list: List<RecipeInformation>) {
        binding?.recipesRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recipesRv?.adapter = RecipesAdapter().apply { setList(list) }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate
}