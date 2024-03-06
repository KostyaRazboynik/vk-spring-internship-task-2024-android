package ru.yandex.quasar.utils.prefs

import kotlinx.coroutines.flow.Flow


interface Preference<T> : Flow<T> {
    suspend fun updateValue(newValue: T)
}
