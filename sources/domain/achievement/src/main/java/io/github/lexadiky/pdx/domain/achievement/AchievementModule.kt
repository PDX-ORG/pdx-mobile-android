package io.github.lexadiky.pdx.domain.achievement

import io.github.lexadiky.pdx.lib.arc.di.module

val AchievementModule by module {
    single { AchievementManager(inject(), inject()) }
    single { AchievementLibraryFactory() }
}