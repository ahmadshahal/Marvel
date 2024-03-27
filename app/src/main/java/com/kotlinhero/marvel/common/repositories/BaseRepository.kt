package com.kotlinhero.marvel.common.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class BaseRepository(
    // TODO: Inject Dispatchers
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

    suspend fun<T> executeOnIO(block: suspend CoroutineScope.() -> T) = withContext(ioDispatcher) {
        block()
    }

    suspend fun<T> executeOnDefault(block: suspend CoroutineScope.() -> T) = withContext(defaultDispatcher) {
        block()
    }
}