package com.prod.finaldraftforprod.common

import kotlinx.coroutines.CoroutineDispatcher

class AppDispatchers(
    val ui: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val unconfined: CoroutineDispatcher
)