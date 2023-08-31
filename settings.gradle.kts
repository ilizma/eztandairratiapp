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
    // di
    ":di-base",

    // View
    ":view-base",

    // Presentation
    ":presentation-base",

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

    // region App
    ":app",
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

    // region Navigation
    ":navigation-di",
    ":navigation-view",
    ":navigation-presentation",
    ":navigation-presentation-imp",
    ":navigation-domain",
    ":navigation-domain-imp",
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

// region Base
project(":di-base").projectDir = File("base-feature/di-base")
project(":presentation-base").projectDir = File("base-feature/presentation-base")
project(":view-base").projectDir = File("base-feature/view-base")
// endregion

// region Net
project(":net-di").projectDir = File("net-feature/net-di")
project(":net").projectDir = File("net-feature/net")
// endregion

// region Api
project(":api-di").projectDir = File("api-feature/api-di")
project(":api").projectDir = File("api-feature/api")
// endregion

// region Main
project(":main-di").projectDir = File("main-feature/main-di")
project(":main-view").projectDir = File("main-feature/main-view")
// endregion

// region Error Management
project(":error-management-di").projectDir = File("error-management-feature/error-management-di")
project(":error-management-view").projectDir = File("error-management-feature/error-management-view")
project(":error-management-view-imp").projectDir = File("error-management-feature/error-management-view-imp")
// endregion

// region Navigation
project(":navigation-di").projectDir = File("navigation-feature/navigation-di")
project(":navigation-view").projectDir = File("navigation-feature/navigation-view")
project(":navigation-presentation").projectDir = File("navigation-feature/navigation-presentation")
project(":navigation-presentation-imp").projectDir = File("navigation-feature/navigation-presentation-imp")
project(":navigation-domain").projectDir = File("navigation-feature/navigation-domain")
project(":navigation-domain-imp").projectDir = File("navigation-feature/navigation-domain-imp")
// endregion

// region Player
project(":player-di").projectDir = File("player-feature/player-di")
project(":player-flow").projectDir = File("player-feature/player-flow")
project(":player-flow-imp").projectDir = File("player-feature/player-flow-imp")
project(":player-view").projectDir = File("player-feature/player-view")
project(":player-presentation").projectDir = File("player-feature/player-presentation")
project(":player-presentation-imp").projectDir = File("player-feature/player-presentation-imp")
project(":player-domain").projectDir = File("player-feature/player-domain")
project(":player-domain-imp").projectDir = File("player-feature/player-domain-imp")
project(":player-data").projectDir = File("player-feature/player-data")
project(":player-data-imp").projectDir = File("player-feature/player-data-imp")
project(":player-framework").projectDir = File("player-feature/player-framework")
project(":player-framework-imp").projectDir = File("player-feature/player-framework-imp")
// endregion

// region Schedule
project(":schedule-di").projectDir = File("schedule-feature/schedule-di")
project(":schedule-flow").projectDir = File("schedule-feature/schedule-flow")
project(":schedule-flow-imp").projectDir = File("schedule-feature/schedule-flow-imp")
project(":schedule-view").projectDir = File("schedule-feature/schedule-view")
project(":schedule-presentation").projectDir = File("schedule-feature/schedule-presentation")
project(":schedule-presentation-imp").projectDir = File("schedule-feature/schedule-presentation-imp")
project(":schedule-domain").projectDir = File("schedule-feature/schedule-domain")
project(":schedule-domain-imp").projectDir = File("schedule-feature/schedule-domain-imp")
project(":schedule-data").projectDir = File("schedule-feature/schedule-data")
project(":schedule-data-imp").projectDir = File("schedule-feature/schedule-data-imp")
// endregion

// region Schedule
project(":menu-di").projectDir = File("menu-feature/menu-di")
project(":menu-flow").projectDir = File("menu-feature/menu-flow")
project(":menu-flow-imp").projectDir = File("menu-feature/menu-flow-imp")
project(":menu-view").projectDir = File("menu-feature/menu-view")
project(":menu-presentation").projectDir = File("menu-feature/menu-presentation")
project(":menu-presentation-imp").projectDir = File("menu-feature/menu-presentation-imp")
// endregion

// region Review
project(":review-di").projectDir = File("review-feature/review-di")
project(":review-framework").projectDir = File("review-feature/review-framework")
project(":review-framework-imp").projectDir = File("review-feature/review-framework-imp")
// endregion

// region Cast
project(":cast-di").projectDir = File("cast-feature/cast-di")
project(":cast-flow").projectDir = File("cast-feature/cast-flow")
project(":cast-flow-imp").projectDir = File("cast-feature/cast-flow-imp")
project(":cast-view").projectDir = File("cast-feature/cast-view")
project(":cast-framework").projectDir = File("cast-feature/cast-framework")
project(":cast-framework-imp").projectDir = File("cast-feature/cast-framework-imp")
// endregion