package com.example.mytrainingpal.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class GenericViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel =
            modelClass.getDeclaredConstructor(Application::class.java).newInstance(application)
        return viewModel as T
    }
}