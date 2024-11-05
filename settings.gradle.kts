pluginManagement {
    repositories {
        google()
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

rootProject.name = "Eztanda Irratiapp"
include(
    // region App
    ":app",
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
    ":error-management-view-imp",
    // endregion

    // region Player
    ":player-di",
    ":player-flow",
    ":player-flow-imp",
    ":player-view",
    ":player-presentation",
    ":player-presentation-imp",
    ":player-domain",
    ":player-domain-imp",
    ":player-data",
    ":player-data-imp",
    ":player-framework",
    ":player-framework-imp",
    // endregion

    // region Schedule
    ":schedule-di",
    ":schedule-flow",
    ":schedule-flow-imp",
    ":schedule-view",
    ":schedule-presentation",
    ":schedule-presentation-imp",
    ":schedule-domain",
    ":schedule-domain-imp",
    ":schedule-data",
    ":schedule-data-imp",
    // endregion

    // region Menu
    ":menu-di",
    ":menu-flow",
    ":menu-flow-imp",
    ":menu-view",
    ":menu-presentation",
    ":menu-presentation-imp",
    // endregion

    // region Review
    ":review-di",
    ":review-framework",
    ":review-framework-imp",
    // endregion

    // region Cast
    ":cast-di",
    ":cast-flow",
    ":cast-flow-imp",
    ":cast-view",
    ":cast-framework",
    ":cast-framework-imp",
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
project(":error-management-view-imp").projectDir = File("features/error-management-feature/error-management-view-imp")
// endregion Error Management

// region Player
project(":player-di").projectDir = File("features/player-feature/player-di")
project(":player-flow").projectDir = File("features/player-feature/player-flow")
project(":player-flow-imp").projectDir = File("features/player-feature/player-flow-imp")
project(":player-view").projectDir = File("features/player-feature/player-view")
project(":player-presentation").projectDir = File("features/player-feature/player-presentation")
project(":player-presentation-imp").projectDir = File("features/player-feature/player-presentation-imp")
project(":player-domain").projectDir = File("features/player-feature/player-domain")
project(":player-domain-imp").projectDir = File("features/player-feature/player-domain-imp")
project(":player-data").projectDir = File("features/player-feature/player-data")
project(":player-data-imp").projectDir = File("features/player-feature/player-data-imp")
project(":player-framework").projectDir = File("features/player-feature/player-framework")
project(":player-framework-imp").projectDir = File("features/player-feature/player-framework-imp")
// endregion Player

// region Schedule
project(":schedule-di").projectDir = File("features/schedule-feature/schedule-di")
project(":schedule-flow").projectDir = File("features/schedule-feature/schedule-flow")
project(":schedule-flow-imp").projectDir = File("features/schedule-feature/schedule-flow-imp")
project(":schedule-view").projectDir = File("features/schedule-feature/schedule-view")
project(":schedule-presentation").projectDir = File("features/schedule-feature/schedule-presentation")
project(":schedule-presentation-imp").projectDir = File("features/schedule-feature/schedule-presentation-imp")
project(":schedule-domain").projectDir = File("features/schedule-feature/schedule-domain")
project(":schedule-domain-imp").projectDir = File("features/schedule-feature/schedule-domain-imp")
project(":schedule-data").projectDir = File("features/schedule-feature/schedule-data")
project(":schedule-data-imp").projectDir = File("features/schedule-feature/schedule-data-imp")
// endregion Schedule

// region Menu
project(":menu-di").projectDir = File("features/menu-feature/menu-di")
project(":menu-flow").projectDir = File("features/menu-feature/menu-flow")
project(":menu-flow-imp").projectDir = File("features/menu-feature/menu-flow-imp")
project(":menu-view").projectDir = File("features/menu-feature/menu-view")
project(":menu-presentation").projectDir = File("features/menu-feature/menu-presentation")
project(":menu-presentation-imp").projectDir = File("features/menu-feature/menu-presentation-imp")
// endregion Menu

// region Review
project(":review-di").projectDir = File("features/review-feature/review-di")
project(":review-framework").projectDir = File("features/review-feature/review-framework")
project(":review-framework-imp").projectDir = File("features/review-feature/review-framework-imp")
// endregion Review

// region Cast
project(":cast-di").projectDir = File("features/cast-feature/cast-di")
project(":cast-flow").projectDir = File("features/cast-feature/cast-flow")
project(":cast-flow-imp").projectDir = File("features/cast-feature/cast-flow-imp")
project(":cast-view").projectDir = File("features/cast-feature/cast-view")
project(":cast-framework").projectDir = File("features/cast-feature/cast-framework")
project(":cast-framework-imp").projectDir = File("features/cast-feature/cast-framework-imp")
// endregion Cast
// endregion FEATURES