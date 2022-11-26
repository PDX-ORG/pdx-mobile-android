package io.github.lexadiky.pdx.ui.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Composable
fun <T> delayState(delay: Duration, state: () -> T): State<T> {
    val actualReturn: MutableState<T> = remember(state) {
        mutableStateOf(state())
    }
    val derived = remember(state) { derivedStateOf(state) }
    LaunchedEffect(derived.value) {
        delay(delay)
        actualReturn.value = derived.value
    }

    return actualReturn
}