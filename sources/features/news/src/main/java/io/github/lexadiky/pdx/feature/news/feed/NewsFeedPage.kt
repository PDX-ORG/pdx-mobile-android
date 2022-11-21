@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.feature.news.feed.NewsFeedViewModel
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.pdx
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
fun NewsFeedPage() {
    DIFeature(NewsModule) {
        NewsFeedPageImpl(di.inject())
    }
}

@Composable
internal fun NewsFeedPageImpl(viewModel: NewsFeedViewModel) {
    ErrorDialog(error = viewModel.state.error) {
        viewModel.dismissError()
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2)
    ) {
        items(viewModel.state.items) { item ->
            NewsFeedItem(item)
        }
    }
}

@Composable
private fun NewsFeedItem(item: NewsFeedItem) {
    Card(
        Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2),
            modifier = Modifier.padding(MaterialTheme.sizes.s2)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2)
            ) {
                item.preview?.let { preview ->
                    Image(
                        painter = preview.render(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(MaterialTheme.sizes.sN(10))
                            .clip(MaterialTheme.shapes.extraLarge),
                    )
                }
                Text(
                    text = item.title.render().value,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SuggestionChip(
                    label = { Text(text = item.author) },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.uikit_ic_reddit),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.pdx.externalBrands.reddit
                        )
                    },
                    onClick = { /*TODO*/ }
                )

                Text(text = item.time.render().value)
            }
        }
    }
}


