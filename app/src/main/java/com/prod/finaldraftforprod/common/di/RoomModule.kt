package com.prod.finaldraftforprod.common.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    /*single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "users"
        ).addMigrations(MIGRATION_1_2).build()
    }*/
}