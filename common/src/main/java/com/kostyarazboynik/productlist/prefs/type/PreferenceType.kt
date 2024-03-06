package ru.yandex.quasar.utils.prefs.type

import android.content.SharedPreferences


data class PreferenceType<T>(
    val read: (
        preferences: SharedPreferences,
        key: String,
        defaultValue: T
    ) -> T,
    val write: (
        editor: SharedPreferences.Editor,
        key: String,
        value: T
    ) -> Unit
) {

    companion object

}
