package com.prod.bookit.common.di

import com.prod.bookit.data.repository.AuthRepositoryImpl
import com.prod.bookit.data.repository.SettingsRepositoryImpl
import com.prod.bookit.domain.repository.AuthRepository
import com.prod.bookit.domain.repository.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}