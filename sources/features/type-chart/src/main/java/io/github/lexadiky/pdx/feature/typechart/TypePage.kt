@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.typechart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartPage
import io.github.lexadiky.pdx.feature.typechart.entity.TypePageTabs
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchPage
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
fun TypePage() {
    DIFeature(TypeModule) {
        TypePageImpl()
    }
}

@Composable
private fun TypePageImpl() {
    val tabs = remember { TypePageTabs.values() }
    var selectedTab by remember(tabs) { mutableStateOf(tabs.first()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabs.indexOf(selectedTab)) {
            TypePageTabs.values().forEach { tab ->
                Tab(selected = selectedTab == tab, onClick = { selectedTab = tab }, text = {
                    Text(text = tab.title.render().value)
                })
            }
        }
        when (selectedTab) {
            TypePageTabs.Chart -> TypeChartPage()
            TypePageTabs.Search -> TypeSearchPage()
        }
    }
}