package ru.yandex.quasar.utils.prefs.shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext
import ru.yandex.quasar.utils.prefs.Preference
import ru.yandex.quasar.utils.prefs.type.PreferenceType


class SharedPreference<T>(
    private val preferencesProvider: SharedPreferencesProvider,
    private val key: String,
    private val type: PreferenceType<T>,
    private val defaultValue: T
) : Preference<T> {

    private val updatedValue = MutableStateFlow<Any?>(NULL)

    @Suppress("UNCHECKED_CAST")
    private val value = combine(
        flow<Any?> {
            emit(NULL)
            val value = withContext(Dispatchers.IO) {
                type.read(preferencesProvider.getPreferences(), key, defaultValue)
            }
            emit(value)
        },
        updatedValue
    ) { readValue, updatedValue ->
        when {
            updatedValue === NULL -> readValue
            else -> updatedValue
        }
    }
        .filter { it !== NULL }
        .map { it as T }
        .shareIn(
            scope = preferencesProvider.coroutineScope,
            replay = 1,
            started = SharingStarted.Lazily
        )


    override suspend fun updateValue(
        newValue: T
    ) {
        withContext(Dispatchers.IO) {
            preferencesProvider.getPreferences().edit().apply {
                type.write(this, key, newValue)
                apply()
            }
        }
        updatedValue.emit(newValue)
    }

    override suspend fun collect(
        collector: FlowCollector<T>
    ) = value.collect(collector)

    companion object {
        private val NULL = Any()
    }
}
