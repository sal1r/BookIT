package com.prod.finaldraftforprod.common.di

import com.prod.finaldraftforprod.data.repository.AuthRepositoryImpl
import com.prod.finaldraftforprod.data.repository.SettingsRepositoryImpl
import com.prod.finaldraftforprod.domain.repository.AuthRepository
import com.prod.finaldraftforprod.domain.repository.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}