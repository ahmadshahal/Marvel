package com.kotlinhero.marvel

import android.app.Application
import com.kotlinhero.marvel.characters.di.CharactersModule
import com.kotlinhero.marvel.main.di.MainModule
import com.kotlinhero.marvel.network.di.KtorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(
                MainModule,
                // CommonModule,
                CharactersModule,
                KtorModule
            )
        }
    }
}