package ru.yandex.quasar.utils.extensions

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


fun <T> or(flows: List<Flow<T>>, reducer: (List<T>) -> T) = channelFlow {
    val array = Array(flows.size) {
        false to (null as T?) // first is "present" or not
    }
    flows.forEachIndexed { index, flow ->
        launch(CoroutineName("flow.or")) {
            flow.collect { element ->
                array[index] = true to element
                val res = array.filter { it.first }.map { it.second }
                val reduced = reducer(res.filterNotNull())
                send(reduced)
            }
        }
    }
}

fun or(flows: List<Flow<Boolean>>) = or(flows) { it.reduce { acc, b -> acc || b } }


@Suppress("UNCHECKED_CAST")
fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = combine(flow, flow2, flow3, flow4, flow5, flow6) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6
    )
}

fun <T> Flow<T>.emitNull() = (this as Flow<T?>).onStart { emit(null) }

fun <T> Flow<T>.startWith(value: T): Flow<T> = onStart { emit(value) }
