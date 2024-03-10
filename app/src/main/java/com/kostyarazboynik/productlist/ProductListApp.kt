package com.kostyarazboynik.productlist

import android.app.Application
import android.os.SystemClock
import com.facebook.drawee.backends.pipeline.Fresco
import com.kostyarazboynik.productlist.dagger.AppComponent
import com.kostyarazboynik.productlist.timer.StartTimeHolder

class ProductListApp : Application() {

    private var isInitialized = false

    override fun onCreate() {
        super.onCreate()
        Logger.setIsDebug(true)
        Logger.d(TAG, "Start time is ${StartTimeHolder.timer.elapsed()}ms")

        appStartTime = SystemClock.elapsedRealtime()

        initialize()
    }

    private fun initialize() {
        if (isInitialized) {
            Logger.d(TAG, "already initialized")
            return
        }
        Logger.d(TAG, "already initializing")
        isInitialized = true
        AppComponent.init(this)
        AppComponent.component.inject(this)

        Fresco.initialize(this)
    }

    companion object {
        private const val TAG = "ProductListApp"
        var appStartTime = 0L
            private set
    }
}
