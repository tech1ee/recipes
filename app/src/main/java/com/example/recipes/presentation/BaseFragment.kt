package com.example.recipes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected val binding: T?
        get() = _binding
    private var _binding: T? = null

    private var act: Act? = null

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        state: Bundle?
    ): View? {
        act = requireActivity() as Act
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        act = null
    }

    protected fun openScreen(fragment: Fragment, backAvailable: Boolean = true) {
        if (backAvailable) act?.openScreen(fragment)
        else act?.openScreenNoBack(fragment)
    }

    protected fun showAlert(title: String, message: String, positiveButtonClicked: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { d, _ ->
                positiveButtonClicked()
                d.dismiss()
            }
            .show()
    }
}