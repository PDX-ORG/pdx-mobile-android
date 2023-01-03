@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import io.github.lexadiky.pdx.feature.drawer.Drawer
import io.github.lexadiky.pdx.feature.toolbar.Toolbar
import io.github.lexadiky.pdx.feature.toolbar.rememberToolbarConnector
import io.github.lexadiky.pdx.lib.arc.di.DIApplication
import io.github.lexadiky.pdx.lib.navigation.NavigationFeature
import io.github.lexadiky.pdx.lib.navigation.NavigationHost
import io.github.lexadiky.pdx.ui.uikit.theme.PdxTheme
import io.github.lexadiky.pdx.ui.uikit.widget.scaffold.PdxScaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val application = application as PdxApplication

        setContent {
            DIApplication(application.diContainer) {
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        PdxTheme {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val toolbarConnector = rememberToolbarConnector(
                onDrawerToggled = { if (drawerState.isOpen) drawerState.close() else drawerState.open() }
            )

            NavigationFeature(routing()) {
                PdxScaffold(
                    drawerState = drawerState,
                    drawerContent = { Drawer() },
                    topBar = { Toolbar(toolbarConnector) },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            NavigationHost()
                        }
                    }
                )
            }
        }
    }
}
