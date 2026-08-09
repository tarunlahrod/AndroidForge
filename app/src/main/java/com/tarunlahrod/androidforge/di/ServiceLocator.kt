package com.tarunlahrod.androidforge.di

/**
 * Global access point to the single `AppContainer`.
 */
object ServiceLocator {
    val container by lazy {
        AppContainer()
    }
}