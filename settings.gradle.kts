pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("./sources/plugins/android")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    defaultLibrariesExtensionName.set("projectLibs")
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "pdx-mobile"

includeBuild("sources/plugins")
includeRecursive(file("sources"))

fun includeRecursive(dir: File) {
    dir.walkTopDown().maxDepth(3).forEach { subDir ->
        if (isModule(subDir)) {
            val moduleName = createModuleName(subDir, dir)

            include(moduleName)
            project(moduleName).projectDir = subDir
        }
    }
}

fun isModule(dir: File): Boolean {
    return File(dir, "build.gradle.kts").exists() || File(dir, "build.gradle.kts.kts").exists()
}

fun createModuleName(subDir: File, dir: File): String {
    var moduleName = ":${subDir.name}"
    var currentDir = subDir.parentFile

    while (currentDir != null) {
        moduleName = ":${currentDir.name}" + moduleName

        currentDir = if (currentDir == dir) {
            null
        } else {
            currentDir.parentFile
        }
    }
    return moduleName
}
