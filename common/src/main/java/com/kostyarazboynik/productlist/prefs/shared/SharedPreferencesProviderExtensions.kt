package ru.yandex.quasar.utils.prefs.shared


import ru.yandex.quasar.utils.prefs.type.PreferenceType
import ru.yandex.quasar.utils.prefs.type.boolean
import ru.yandex.quasar.utils.prefs.type.long
import ru.yandex.quasar.utils.prefs.type.string


fun SharedPreferencesProvider.bool(
    key: String,
    defaultValue: Boolean = false
) = SharedPreference(
    preferencesProvider = this,
    type = PreferenceType.boolean,
    key = key,
    defaultValue = defaultValue
)

fun SharedPreferencesProvider.long(
    key: String,
    defaultValue: Long = 0
) = SharedPreference(
    preferencesProvider = this,
    type = PreferenceType.long,
    key = key,
    defaultValue = defaultValue
)

fun SharedPreferencesProvider.string(
    key: String,
    defaultValue: String? = null
) = SharedPreference(
    preferencesProvider = this,
    type = PreferenceType.string,
    key = key,
    defaultValue = defaultValue
)
