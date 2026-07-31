@file:Suppress("UnstableApiUsage")

rootProject.name = "EztandaIrratiapp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    // region App
    ":composeApp",
    // endregion

    // View
    ":view-base",

    // resources
    ":resources",

    // region Net
    ":net-di",
    ":net",
    // endregion

    // region Api
    ":api-di",
    ":api",
    // endregion

    // region Main
    ":main-di",
    ":main-view",
    // endregion

    // region Error Management
    ":error-management-di",
    ":error-management-view",
    // endregion

    // region Player
    ":player-di",
    ":player-flow",
    ":player-view",
    ":player-presentation",
    ":player-domain",
    ":player-data",
    ":player-framework",
    // endregion

    // region Schedule
    ":schedule-di",
    ":schedule-flow",
    ":schedule-view",
    ":schedule-presentation",
    ":schedule-domain",
    ":schedule-data",
    // endregion

    // region Menu
    ":menu-di",
    ":menu-flow",
    ":menu-view",
    ":menu-presentation",
    // endregion

    // region Review
    ":review-di",
    ":review-framework",
    // endregion

    // region Cast
    ":cast-di",
    ":cast-flow",
    ":cast-view",
    ":cast-framework",
    // endregion

)

// region CORE
// region Resources
project(":resources").projectDir = File("core/resources")
// endregion Resources

// region Base
project(":view-base").projectDir = File("core/view-base")
// endregion Base
// endregion CORE

// region FEATURES
// region Net
project(":net-di").projectDir = File("features/net-feature/net-di")
project(":net").projectDir = File("features/net-feature/net")
// endregion NEt

// region Api
project(":api-di").projectDir = File("features/api-feature/api-di")
project(":api").projectDir = File("features/api-feature/api")
// endregion Api

// region Main
project(":main-di").projectDir = File("features/main-feature/main-di")
project(":main-view").projectDir = File("features/main-feature/main-view")
// endregion Main

// region Error Management
project(":error-management-di").projectDir = File("features/error-management-feature/error-management-di")
project(":error-management-view").projectDir = File("features/error-management-feature/error-management-view")
// endregion Error Management

// region Player
project(":player-di").projectDir = File("features/player-feature/player-di")
project(":player-flow").projectDir = File("features/player-feature/player-flow")
project(":player-view").projectDir = File("features/player-feature/player-view")
project(":player-presentation").projectDir = File("features/player-feature/player-presentation")
project(":player-domain").projectDir = File("features/player-feature/player-domain")
project(":player-data").projectDir = File("features/player-feature/player-data")
project(":player-framework").projectDir = File("features/player-feature/player-framework")
// endregion Player

// region Schedule
project(":schedule-di").projectDir = File("features/schedule-feature/schedule-di")
project(":schedule-flow").projectDir = File("features/schedule-feature/schedule-flow")
project(":schedule-view").projectDir = File("features/schedule-feature/schedule-view")
project(":schedule-presentation").projectDir = File("features/schedule-feature/schedule-presentation")
project(":schedule-domain").projectDir = File("features/schedule-feature/schedule-domain")
project(":schedule-data").projectDir = File("features/schedule-feature/schedule-data")
// endregion Schedule

// region Menu
project(":menu-di").projectDir = File("features/menu-feature/menu-di")
project(":menu-flow").projectDir = File("features/menu-feature/menu-flow")
project(":menu-view").projectDir = File("features/menu-feature/menu-view")
project(":menu-presentation").projectDir = File("features/menu-feature/menu-presentation")
// endregion Menu

// region Review
project(":review-di").projectDir = File("features/review-feature/review-di")
project(":review-framework").projectDir = File("features/review-feature/review-framework")
// endregion Review

// region Cast
project(":cast-di").projectDir = File("features/cast-feature/cast-di")
project(":cast-flow").projectDir = File("features/cast-feature/cast-flow")
project(":cast-view").projectDir = File("features/cast-feature/cast-view")
project(":cast-framework").projectDir = File("features/cast-feature/cast-framework")
// endregion Cast
// endregion FEATURES