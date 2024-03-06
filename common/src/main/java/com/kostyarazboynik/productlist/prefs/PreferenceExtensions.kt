package ru.yandex.quasar.utils.prefs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private class MappedPreference<I, O>(
    private val base: Preference<I>,
    private val mapper: Mapper<I, O>
) : Preference<O>, Flow<O> by (base.map { mapper.direct(it) }) {

    override suspend fun updateValue(newValue: O) =
        base.updateValue(mapper.reverse(newValue))
}

fun <I, O> Preference<I>.map(mapper: Mapper<I, O>): Preference<O> =
    MappedPreference(this, mapper)


data class Mapper<I, O>(
    val direct: (I) -> O,
    val reverse: (O) -> I
) {
    companion object
}

