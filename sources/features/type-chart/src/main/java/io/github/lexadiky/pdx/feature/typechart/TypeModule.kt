package io.github.lexadiky.pdx.feature.typechart

import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartViewModel
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchViewModel
import io.github.lexadiky.pdx.lib.arc.di.module

val TypeModule by module {
    import(PokemonDomainModule)

    internal {
        single { TypeChartViewModel(inject()) }
        single { TypeSearchViewModel(inject(), inject(), inject(), inject()) }
        single { PokemonSuggester() }
    }
}