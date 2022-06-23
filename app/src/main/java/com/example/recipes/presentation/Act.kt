package com.example.recipes.presentation

import androidx.fragment.app.Fragment

interface Act {

    fun openScreen(fragment: Fragment)

    fun openScreenNoBack(fragment: Fragment)
}