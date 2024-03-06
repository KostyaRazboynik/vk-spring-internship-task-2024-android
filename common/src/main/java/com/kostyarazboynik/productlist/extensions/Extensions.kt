package com.kostyarazboynik.productlist.extensions

import android.content.ContextWrapper
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.reflect.KClass


inline fun <T> T?.elvis(ifNull: () -> T) = this ?: ifNull()

inline fun <reified O> Any?.castOrElse(
    elseAction: () -> O
) = (this as? O) ?: elseAction()

inline fun <reified O> Any?.castOrNull() =
    castOrElse<O?> { null }

inline fun <reified O> Any?.castOrThrow() =
    castOrElse<O> { error("Unable to cast $this to ${O::class}") }

fun <T> it(it: T) = it

suspend fun Iterable<suspend () -> Boolean>.executeAll() = coroutineScope {
    map { asyncOperation ->
        async { asyncOperation() }
    }
        .awaitAll()
        .all(::it)
}

inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T = apply {
    if (condition) block(this)
}

inline fun <T> runIf(condition: Boolean, runBlock: () -> T): T? =
    if (condition) runBlock() else null

tailrec fun Context.findActivityInWrappers(): Activity? {
    if (this is Activity) return this
    if (this is ContextWrapper) return baseContext?.findActivityInWrappers()

    return null
}

@MainThread
inline fun <reified VM : ViewModel> View.viewModels(
    storeOwner: ViewModelStoreOwner? = findViewTreeViewModelStoreOwner(),
    noinline factoryProducer: () -> ViewModelProvider.Factory
): Lazy<VM> = createViewModelLazy(
    VM::class, {
        storeOwner?.let { return@createViewModelLazy it.viewModelStore }

        try {
            return@createViewModelLazy FragmentManager.findFragment<Fragment>(this).viewModelStore
        } catch (e: Exception){ Unit }

        (context.findActivityInWrappers() as? AppCompatActivity)?.let { return@createViewModelLazy it.viewModelStore }

        throw IllegalArgumentException("ViewTreeViewModelStoreOwner not found")
    },
    factoryProducer
)

@MainThread
fun <VM : ViewModel> createViewModelLazy(
    viewModelClass: KClass<VM>,
    storeProducer: () -> ViewModelStore,
    factoryProducer: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return ViewModelLazy(viewModelClass, storeProducer, factoryProducer)
}
