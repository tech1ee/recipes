package com.example.recipes.presentation.recipedetails.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeOverviewBinding
import com.example.recipes.entity.RecipeInformation
import com.example.recipes.presentation.BaseFragment
import com.example.recipes.presentation.recipedetails.RecipeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeOverviewFragment : BaseFragment<FragmentRecipeOverviewBinding>() {

    private val recipeDetailsViewModel by viewModels<RecipeDetailsViewModel>(
        ownerProducer = { requireParentFragment() }
    )
    private val recipeOverviewViewModel by viewModels<RecipeOverviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() {
        recipeDetailsViewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            state.recipeId?.let { id ->
                recipeOverviewViewModel.setup(id, true)
            }
        }
        recipeOverviewViewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            progress(state.loading)
            if (state.data != null) handleData(state.data)
            if (state.errorMessage != null) handleError(state.errorMessage)
        }
    }

    private fun progress(inProgress: Boolean) {
        binding?.progressBar?.visibility = if (inProgress) View.VISIBLE else View.GONE
    }

    private fun handleData(data: RecipeInformation) {
        data.title?.let { title ->
            recipeDetailsViewModel.setRecipeTitle(title)
        }
        binding?.image?.let { imageView ->
            Glide.with(requireContext()).load(data.image).into(imageView)
        }
        data.summary?.let { txt ->
            binding?.summary?.text = HtmlCompat.fromHtml(txt, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }

    private fun handleError(message: String) {
        showAlert(
            title = getString(R.string.error),
            message = message
        ) { requireActivity().onBackPressed() }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeOverviewBinding
        get() = FragmentRecipeOverviewBinding::inflate

}