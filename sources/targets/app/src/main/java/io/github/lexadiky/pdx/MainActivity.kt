@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import io.github.lexadiky.pdx.feature.drawer.Drawer
import io.github.lexadiky.pdx.feature.news.NewsFeedPage
import io.github.lexadiky.pdx.feature.settings.SettingsPage
import io.github.lexadiky.pdx.feature.toolbar.Toolbar
import io.github.lexadiky.pdx.feature.toolbar.rememberToolbarConnector
import io.github.lexadiky.pdx.lib.arc.di.DIApplication
import io.github.lexadiky.pdx.lib.arc.di.module
import io.github.lexadiky.pdx.lib.navigation.NavigationFeature
import io.github.lexadiky.pdx.lib.navigation.NavigationHost
import io.github.lexadiky.pdx.lib.network.NetworkModule
import io.github.lexadiky.pdx.ui.theme.PdxTheme
import io.github.lexadiky.pdx.ui.uikit.widget.scaffold.PdxScaffold
import io.github.lexadiky.pdx.ui.uikit.image.rememberAsyncImagePainter
import io.github.lexadiky.pdx.ui.uikit.image.transformation.CropTransparentTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as PdxApplication

        setContent {
            DIApplication(application.startupModules) {
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

            NavigationFeature {
                PdxScaffold(
                    drawerState = drawerState,
                    drawerContent = { Drawer() },
                    topBar = { Toolbar(toolbarConnector) },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            NavigationHost {
                                composable("pdx://index") {
                                    Text(text = "INDEX")
                                }
                                composable("pdx://settings") {
                                    SettingsPage()
                                }
                                composable("pdx://news") {
                                    NewsFeedPage()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PdxTheme {
        Greeting("Android")
    }
}