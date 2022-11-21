package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class ToolbarConnector(
    val onDrawerToggled: suspend () -> Unit
)

@Composable
fun rememberToolbarConnector(
    onDrawerToggled: suspend () -> Unit
): ToolbarConnector = remember {
    ToolbarConnector(onDrawerToggled)
}
