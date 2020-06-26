package fi.henrimakela.backbase_challenge

import android.app.Application
import fi.henrimakela.backbase_challenge.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(applicationModule)
        }
    }
}