package ru.yandex.quasar.utils.prefs.shared

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred


interface SharedPreferencesProvider {
    val coroutineScope: CoroutineScope
    suspend fun getPreferences(): SharedPreferences
}
