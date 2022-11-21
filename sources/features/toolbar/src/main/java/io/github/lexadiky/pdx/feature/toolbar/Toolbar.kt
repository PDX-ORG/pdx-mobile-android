@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.render
import kotlinx.coroutines.launch

@Composable
fun Toolbar(connector: ToolbarConnector) {
    DIFeature(ToolbarModule) {
        ToolbarImpl(viewModel = di.inject(connector))
    }
}

@Composable
internal fun ToolbarImpl(viewModel: ToolbarViewModel) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        title = {
            Text(
                text = viewModel.state.title.render().value
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { viewModel.openDrawer() } },
                content = { Icon(Icons.Default.Menu, null) }
            )
        },
        actions = {
            AssistChip(
                onClick = { /*TODO*/ },
                label = { Text(text = "Click Me") },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    )
}