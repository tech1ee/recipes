package com.example.recipes.presentation.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recipes.databinding.FragmentRecipeDetailsBinding
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipedetails.ingredients.RecipeIngredientsFragment
import com.example.recipes.presentation.recipedetails.overview.RecipeOverviewFragment

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private val viewModel by viewModels<RecipeDetailsViewModel>(
        ownerProducer = { this }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        arguments?.getLong(EXTRA_ID)?.let { id ->
            viewModel.setup(id)
        }
    }

    private fun initView() {
        binding?.viewPager?.adapter = RecipeDetailsPagerAdapter()
    }

    private fun observe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            state.recipeTitle?.let { title ->
                binding?.toolbar?.title = title
            }
        }
    }

    private inner class RecipeDetailsPagerAdapter: FragmentStateAdapter(this) {

        override fun getItemCount() = PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> RecipeIngredientsFragment()
                else -> RecipeOverviewFragment()
            }
        }

    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeDetailsBinding
        get() = FragmentRecipeDetailsBinding::inflate

    companion object {

        private const val PAGES = 2
        private const val EXTRA_ID = "EXTRA_ID"

        fun newInstance(id: Long) = RecipeDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_ID, id)
            }
        }
    }
}