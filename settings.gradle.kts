pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidDemo"
include(":app")
include(":feature")
include(":core")
include(":build_logic")
include(":feature:handler")
include(":feature:GestureOverlayView")
include(":feature:RoomDemo")
include(":feature:Calendar")
include(":feature:Sideslip")
include(":feature:AdapterDelegatesDemo")
include(":core:common")
