@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import io.github.lexadiky.pdx.lib.arc.di.DIApplication
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.navigation.NavigationFeature
import io.github.lexadiky.pdx.lib.navigation.NavigationHost
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.ui.uikit.theme.PdxTheme
import kotlinx.coroutines.launch

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
            NavigationFeature(routing()) {
                val navigator = di.inject<Navigator>()
                val scope = rememberCoroutineScope()

                Column {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_toolbar_title)) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    navigator.navigate("pdx://settings")
                                }
                            }
                            ) {
                                Icon(Icons.Default.Settings, null)
                            }
                        }
                    )
                    NavigationHost()
                }
            }
        }
    }
}
