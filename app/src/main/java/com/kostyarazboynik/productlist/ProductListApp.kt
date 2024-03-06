package com.kostyarazboynik.productlist

import android.app.Application
import android.os.SystemClock
import com.kostyarazboynik.productlist.dagger.AppComponent
import com.kostyarazboynik.productlist.timer.StartTimeHolder

class ProductListApp : Application() {

    private var isInitialized = false

    override fun onCreate() {
        super.onCreate()
        Logger.setIsDebug(true)
        Logger.d(TAG, "Start time is ${StartTimeHolder.timer.elapsed()}ms")

        appStartTime = SystemClock.elapsedRealtime()

        onUserUnlocked()
    }

    private fun onUserUnlocked() {
        Logger.d(TAG, "onUserUnlocked")
        if (isInitialized) {
            return
        }
        isInitialized = true
        AppComponent.init(this)
        AppComponent.component.inject(this)
        Logger.d(TAG, "onUserUnlocked")

    }

    companion object {
        private const val TAG = "ProductListApp"
        var appStartTime = 0L
            private set
    }
}
