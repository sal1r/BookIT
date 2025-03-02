package com.prod.bookit.common.di

import com.prod.bookit.data.repository.AuthRepositoryImpl
import com.prod.bookit.data.repository.CoworkingsRepositoryImpl
import com.prod.bookit.data.repository.ProfileRepositoryImpl
import com.prod.bookit.data.repository.SettingsRepositoryImpl
import com.prod.bookit.domain.repository.CoworkingsRepository
import com.prod.bookit.domain.repository.AuthRepository
import com.prod.bookit.domain.repository.ProfileRepository
import com.prod.bookit.domain.repository.SettingsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<CoworkingsRepository> { CoworkingsRepositoryImpl(get()) }

    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}