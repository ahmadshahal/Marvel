package com.kotlinhero.marvel.main.di

import com.kotlinhero.marvel.main.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    viewModel { MainViewModel() }
}