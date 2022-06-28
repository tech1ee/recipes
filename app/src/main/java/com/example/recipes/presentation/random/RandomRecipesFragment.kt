package com.example.recipes.presentation.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.FragmentRandomRecipesBinding
import com.example.recipes.entity.RecipeInformation
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipelist.RecipesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    private val viewModel by viewModels<RandomRecipesViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        viewModel.getRandomRecipes()
    }

    private fun initView() {
        binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.getRandomRecipes()
        }
    }

    private fun observe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            handleProgress(state.loading)
            state.recipes?.let { recipes -> handleRecipeList(recipes) }
            state.errorMessage?.let { message ->
                binding?.root?.let { root -> Snackbar.make( root, message, Toast.LENGTH_SHORT).show() }
            }
        }
    }

    private fun handleRecipeList(list: List<RecipeInformation>) {
        binding?.recipesRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recipesRv?.adapter = RecipesAdapter().apply { setList(list) }
    }

    private fun handleProgress(inProgress: Boolean) {
        binding?.swipeRefresh?.isRefreshing = inProgress
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate
}