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
    ":app-di",
    ":app-flow",
    ":app-flow-imp",
    ":app-view",
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
    ":player-view-imp",
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
    ":schedule-view-imp",
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
    ":menu-view-imp",
    ":menu-presentation",
    ":menu-presentation-imp",
    // endregion

    // region Review
    ":review-di",
    ":review-framework",
    ":review-framework-imp",
    // endregion

)

// region Net
project(":net-di").projectDir = File("net-feature/net-di")
project(":net").projectDir = File("net-feature/net")
// endregion

// region Api
project(":api-di").projectDir = File("api-feature/api-di")
project(":api").projectDir = File("api-feature/api")
// endregion

// region App
project(":app-di").projectDir = File("app-feature/app-di")
project(":app-flow").projectDir = File("app-feature/app-flow")
project(":app-flow-imp").projectDir = File("app-feature/app-flow-imp")
project(":app-view").projectDir = File("app-feature/app-view")
// endregion

// region Error Management
project(":error-management-di").projectDir = File("error-management-feature/error-management-di")
project(":error-management-view").projectDir =
    File("error-management-feature/error-management-view")
project(":error-management-view-imp").projectDir =
    File("error-management-feature/error-management-view-imp")
// endregion

// region Player
project(":player-di").projectDir = File("player-feature/player-di")
project(":player-flow").projectDir = File("player-feature/player-flow")
project(":player-flow-imp").projectDir = File("player-feature/player-flow-imp")
project(":player-view").projectDir = File("player-feature/player-view")
project(":player-view-imp").projectDir = File("player-feature/player-view-imp")
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
project(":schedule-view-imp").projectDir = File("schedule-feature/schedule-view-imp")
project(":schedule-presentation").projectDir = File("schedule-feature/schedule-presentation")
project(":schedule-presentation-imp").projectDir =
    File("schedule-feature/schedule-presentation-imp")
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
project(":menu-view-imp").projectDir = File("menu-feature/menu-view-imp")
project(":menu-presentation").projectDir = File("menu-feature/menu-presentation")
project(":menu-presentation-imp").projectDir = File("menu-feature/menu-presentation-imp")
// endregion

// region Review
project(":review-di").projectDir = File("review-feature/review-di")
project(":review-framework").projectDir = File("review-feature/review-framework")
project(":review-framework-imp").projectDir = File("review-feature/review-framework-imp")
// endregion