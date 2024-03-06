package ru.yandex.quasar.utils.prefs.shared

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AwaitUserUnlockedSharedPreferencesProvider(
    private var context: Context,
    override val coroutineScope: CoroutineScope,
    private val preferencesFileName: String
) : SharedPreferencesProvider {

    private var preferences: SharedPreferences? = null
    private val getPreferencesMutex = Mutex()

    override suspend fun getPreferences() = getPreferencesMutex.withLock<SharedPreferences> {
        when (val preferencesLocal = preferences) {
            null -> {
                context
                    .getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)
                    .also { preferences = it }
            }
            else -> preferencesLocal
        }
    }
}
