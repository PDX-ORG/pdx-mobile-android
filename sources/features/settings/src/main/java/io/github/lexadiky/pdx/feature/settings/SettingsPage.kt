@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.settings

import android.graphics.fonts.FontStyle
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.google.android.material.color.utilities.Scheme
import com.google.android.material.color.utilities.TonalPalette
import io.github.lexadiky.pdx.feature.settings.R.*
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.ui.uikit.theme.custom.ThemeManager
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
fun SettingsPage() {
    DIFeature(SettingsPageModule) {
        SettingsPageImpl(di.inject())
    }
}

@Composable
private fun SettingsPageImpl(viewModel: SettingsPageViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(
                    id = string.settings_themes_header,
                    viewModel.state.currentTheme?.id.orEmpty()
                ),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(MaterialTheme.sizes.s2)
            )
            ThemeSelection(viewModel.state) {
                viewModel.onThemeSelected(it)
            }
        }
        item {
            Divider()
        }
        item {
            ListItem(
                headlineText = { Text(text = stringResource(id = string.settings_github_header)) },
                supportingText = { Text(text = stringResource(id = string.settings_github_description)) },
                trailingContent = {
                    Icon(painterResource(R.drawable.uikit_ic_github), null)
                },
                modifier = Modifier.clickable { viewModel.openGithub() }
            )
        }
        item {
            ListItem(
                headlineText = { Text(text = stringResource(id = string.settings_cache_header)) },
                supportingText = { Text(text = stringResource(id = string.settings_cache_description)) },
                trailingContent = {
                    Icon(Icons.Default.Delete, null)
                },
                modifier = Modifier.clickable {  }
            )
        }
    }
}

@Composable
private fun ThemeSelection(state: SettingsPageState, onThemeSelected: (CustomTheme) -> Unit) {
    val sizeSmall = MaterialTheme.sizes.sN(10)
    val sizeBig = MaterialTheme.sizes.sN(12)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = MaterialTheme.sizes.s2),
        modifier = Modifier
            .height(sizeBig + MaterialTheme.sizes.s2 * 2)
    ) {
        items(state.availableThemes) { theme ->
            ThemeBox(
                sizeBig = sizeBig,
                sizeSmall = sizeSmall,
                selectedTheme = state.currentTheme,
                theme = theme,
                onClick = { onThemeSelected(theme) }
            )
        }
    }
}

@Composable
private fun ThemeBox(
    sizeBig: Dp,
    sizeSmall: Dp,
    selectedTheme: CustomTheme?,
    theme: CustomTheme,
    onClick: () -> Unit
) {
    val size by animateDpAsState(if (selectedTheme?.id == theme.id) sizeBig else sizeSmall)
    val cornerRadius by animateDpAsState(if (selectedTheme?.id == theme.id) sizeSmall else 32.dp)

    Box(
        Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .clickable { onClick() }
            .background(theme.colorScheme.primary)
            .size(size)
    )
}