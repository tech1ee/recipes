package com.example.recipes.presentation.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeDetailsBinding
import com.example.recipes.entity.RecipeInformation
import com.example.recipes.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>() {

    private val viewModel by viewModels<RecipeDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        val id = arguments?.getLong(EXTRA_ID)
        val includeNutrition = arguments?.getBoolean(EXTRA_INCLUDE_NUTRITION)
        if (id != null && includeNutrition != null) viewModel.setup(id, includeNutrition)
        else showAlert(
            title = getString(R.string.error),
            message = getString(R.string.couldnt_get_data)
        ) { requireActivity().onBackPressed() }
    }

    private fun observe() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            progress(state.loading)
            if (state.data != null) setData(state.data)
            if (state.errorMessage != null) showAlert(
                title = getString(R.string.error),
                message = state.errorMessage
            ) { requireActivity().onBackPressed() }
        }
    }

    private fun progress(inProgress: Boolean) {
        binding?.progressBar?.visibility = if (inProgress) View.VISIBLE else View.GONE
    }

    private fun setData(data: RecipeInformation) {
        binding?.toolbar?.title = data.title
        binding?.image?.let { imageView ->
            Glide.with(requireContext()).load(data.image).into(imageView)
        }
        data.summary?.let { txt ->
            binding?.summary?.text = HtmlCompat.fromHtml(txt, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeDetailsBinding
        get() = FragmentRecipeDetailsBinding::inflate


    companion object {

        private const val EXTRA_ID = "EXTRA_ID"
        private const val EXTRA_INCLUDE_NUTRITION = "EXTRA_INCLUDE_NUTRITION"

        fun newInstance(id: Long, includeNutrition: Boolean) = RecipeDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_ID, id)
                putBoolean(EXTRA_INCLUDE_NUTRITION, includeNutrition)
            }
        }
    }

}