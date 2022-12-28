package com.example.mytrainingpal.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class GenericViewModelFactory(val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel =
            modelClass.getDeclaredConstructor(Context::class.java).newInstance(context)
        return viewModel as T
    }
}