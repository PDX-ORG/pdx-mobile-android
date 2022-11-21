package io.github.lexadiky.pdx.lib.navigation.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import io.github.lexadiky.pdx.lib.navigation.R

internal fun NavGraphBuilder.registerPage404() = composable(
    route = "pdx-tech://404",
    deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/.*" })
) { navEntry ->
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.navigation_404_title),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.navigation_404_subtitle),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
