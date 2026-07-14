# Migración de Navigation 2 a Navigation 3

Este plan detalla la migración de la arquitectura de navegación actual (basada en `NavHostController`) a la nueva versión **Navigation 3**. Navigation 3 se basa en un estado de navegación manejado por el usuario (backstack como una lista de claves) y elimina la necesidad de `NavController`.

## User Review Required

> [!IMPORTANT]
> Este cambio es estructural y afecta a todas las interfaces de `Router` y `Navigator` de la aplicación, eliminando las dependencias de `androidx.navigation`.

## Proposed Changes

### [Core] Infraestructura de Navegación

Crearemos las clases base para manejar el estado de navegación según las recomendaciones de Navigation 3.

#### [NEW] [NavKey.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/core/view-base/src/commonMain/kotlin/com/ilizma/core/viewbase/navigation/NavKey.kt)
Interfaz base para todas las rutas de la aplicación.

#### [NEW] [NavigationState.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/core/view-base/src/commonMain/kotlin/com/ilizma/core/viewbase/navigation/NavigationState.kt)
Contenedor del estado de la pila de navegación.

#### [NEW] [Navigator.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/core/view-base/src/commonMain/kotlin/com/ilizma/core/viewbase/navigation/Navigator.kt)
Clase para realizar operaciones de navegación (navigate, goBack).

---

### [Features] Actualización de Rutas

Actualizaremos todas las rutas para que implementen `NavKey`.

#### [MODIFY] [BottomNavigation.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/main-feature/main-view/src/commonMain/kotlin/com/ilizma/main/view/model/BottomNavigation.kt)
#### [MODIFY] [RadioTab.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/player-feature/player-flow/src/commonMain/kotlin/com/ilizma/player/flow/model/RadioTab.kt)
#### [MODIFY] [ScheduleTab.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-flow/src/commonMain/kotlin/com/ilizma/schedule/flow/model/ScheduleTab.kt)
#### [MODIFY] [MenuTab.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/menu-feature/menu-flow/src/commonMain/kotlin/com/ilizma/menu/flow/model/MenuTab.kt)
#### [MODIFY] [ScheduleDetail.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-flow/src/commonMain/kotlin/com/ilizma/schedule/flow/model/ScheduleDetail.kt)

---

### [Features] Refactorización de Routers y Navigators

Eliminaremos el uso de `NavHostController` en las interfaces y sus implementaciones.

#### [MODIFY] [MenuScreenRouter.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/menu-feature/menu-view/src/commonMain/kotlin/com/ilizma/menu/view/router/MenuScreenRouter.kt)
#### [MODIFY] [MenuScreenRouter.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/menu-feature/menu-flow/src/commonMain/kotlin/com/ilizma/menu/flow/router/MenuScreenRouter.kt)
#### [MODIFY] [ScheduleScreenRouter.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-view/src/commonMain/kotlin/com/ilizma/schedule/view/router/ScheduleScreenRouter.kt)
#### [MODIFY] [ScheduleScreenRouterImp.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-flow/src/commonMain/kotlin/com/ilizma/schedule/flow/router/ScheduleScreenRouterImp.kt)
#### [MODIFY] [ScheduleDetailRouter.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-view/src/commonMain/kotlin/com/ilizma/schedule/view/router/ScheduleDetailRouter.kt)
#### [MODIFY] [ScheduleDetailRouterImp.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/schedule-feature/schedule-flow/src/commonMain/kotlin/com/ilizma/schedule/flow/router/ScheduleDetailRouterImp.kt)

---

### [UI] Implementación de NavDisplay

Actualizaremos las pantallas principales para usar `NavDisplay` en lugar de `NavHost`.

#### [MODIFY] [AppNavigation.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/main-feature/main-view/src/commonMain/kotlin/com/ilizma/main/view/compose/AppNavigation.kt)
Configuración de `NavigationState` y `Navigator` para la navegación de primer nivel (BottomNavigation <-> ScheduleDetail).

#### [MODIFY] [BottomNavigation.kt](file:///Users/iosu/Documents/StudioProjects/eztandairratiapp/features/main-feature/main-view/src/commonMain/kotlin/com/ilizma/main/view/compose/BottomNavigation.kt)
Configuración de `NavigationState` y `Navigator` para las pestañas de la barra inferior.

## Verification Plan

### Automated Tests
- Ejecutar compilación del proyecto: `./gradlew assembleDebug` (o equivalente multiplatform).

### Manual Verification
1. Abrir la app y verificar que se muestra la pantalla de Radio por defecto.
2. Navegar entre las pestañas (Radio, Agenda, Menú) y verificar que el estado se mantiene.
3. Desde Agenda, navegar a un detalle de programa y verificar que se muestra correctamente.
4. Volver atrás desde el detalle y verificar que regresa a la pestaña de Agenda.
