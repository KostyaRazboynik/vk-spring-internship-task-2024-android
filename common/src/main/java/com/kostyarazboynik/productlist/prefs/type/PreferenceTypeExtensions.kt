package ru.yandex.quasar.utils.prefs.type

import android.content.SharedPreferences
import ru.yandex.quasar.utils.prefs.type.PreferenceType

val stringPreferenceType = PreferenceType(
    read = SharedPreferences::getString,
    write = SharedPreferences.Editor::putString
)

val longPreferenceType = PreferenceType(
    read = SharedPreferences::getLong,
    write = SharedPreferences.Editor::putLong
)

val booleanPreferenceType = PreferenceType(
    read = SharedPreferences::getBoolean,
    write = SharedPreferences.Editor::putBoolean
)

val PreferenceType.Companion.string
    get() = stringPreferenceType

val PreferenceType.Companion.long
    get() = longPreferenceType

val PreferenceType.Companion.boolean
    get() = booleanPreferenceType
