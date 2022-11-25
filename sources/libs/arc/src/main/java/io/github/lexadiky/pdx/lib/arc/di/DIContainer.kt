package io.github.lexadiky.pdx.lib.arc.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication

class DIContainer(@PublishedApi internal val application: KoinApplication) {

    constructor(vararg modules: DIModule) : this(koinApplication {
        modules(modules.map { it.koinModule })
    })

    @Composable
    inline fun <reified T> inject(): T {
        return remember {
            application.koin.get()
        }
    }

    @Composable
    inline fun <reified T : Any> inject(vararg paramerers: Any): T {
        return remember {
            application.koin.get {
                parametersOf(*paramerers)
            }
        }
    }

    inline fun <reified T : Any> lookup(): T {
        return application.koin.get()
    }
}

private val LocalDIContainer = compositionLocalOf<DIContainer> {
    error("no di container attached")
}

@Composable
fun DIApplication(container: DIContainer, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDIContainer provides container) {
        content()
    }
}

@Composable
fun DIFeature(vararg includes: DIModule, content: @Composable () -> Unit) {
    val diContainer = LocalDIContainer.current
    diContainer.application.modules(includes.map { it.koinModule })
    content()
    DisposableEffect(Unit) {
        onDispose {
            diContainer.application.unloadModules(includes.map { it.koinModule })
        }
    }
}

val di: DIContainer
    @Composable get() = LocalDIContainer.current