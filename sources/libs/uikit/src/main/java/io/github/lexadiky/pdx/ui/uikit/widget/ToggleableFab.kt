package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToggleableFab(
    isToggled: Boolean,
    toggled: @Composable () -> Unit,
    untoggled: @Composable () -> Unit,
    onToggle: () -> Unit
) {
    FloatingActionButton(onClick = { onToggle() }) {
        AnimatedContent(
            targetState = isToggled,
            transitionSpec = {
                scaleIn() with scaleOut()
            }
        ) { isToggled ->
            if (isToggled) {
                toggled()
            } else {
                untoggled()
            }
        }
    }
}