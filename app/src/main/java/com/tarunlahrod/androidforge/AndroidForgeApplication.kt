package com.tarunlahrod.androidforge

import android.app.Application
import com.tarunlahrod.androidforge.di.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AndroidForgeApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    private val applicationScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(applicationContext)

        // restore the app's authentication state
        applicationScope.launch {
            appContainer.authSession.restore()
        }
    }
}