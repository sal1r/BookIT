package com.prod.finaldraftforprod.common.di

import com.prod.finaldraftforprod.presentation.viewModels.AuthViewModel
import com.prod.finaldraftforprod.presentation.viewModels.SettingsViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { AuthViewModel(get()) }

    single { SettingsViewModel(get(), get(), get()) }
}