package com.meowsoft.sosbutton.common.di.screens

import com.meowsoft.sosbutton.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {

    viewModel {
        MainActivityViewModel()
    }
}