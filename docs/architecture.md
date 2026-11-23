# Architecture

## Layers

The app follows a lightweight clean-style split inside `shared/src/commonMain`:

| Layer | Location | Responsibility |
| --- | --- | --- |
| UI | `feature/**/ui/` | Composables, ViewModels, state/event classes |
| Domain | `feature/**/domain/` | Models, repository interfaces, use cases |
| Data | `feature/**/data/` | Repository implementations, remote data sources, mappers |
| Network | `feature/network/` | Ktor client, request/response DTOs |
| DI | `feature/di/` | Koin modules |

## Data flow

```
Composable  --event-->  ViewModel  --use case-->  Repository
     ^                      |                         |
     |                      |                    RemoteDataSource
     +---- StateFlow -------+                         |
                                                Ktor HttpClient
```

Example (`HomeTabViewModel`):

- `HomeTabState.kt` holds both the state (`HomeTabState`) and the events (`HomeTabEvents`) in one file.
- `HomeTabView` collects `viewModel.state` with `collectAsState()` and dispatches `HomeTabEvents.LoadData` from `LaunchedEffect`.
- `HomeTabViewModel.doAction(event)` routes events to private load functions.
- Loading/success/error are represented by `Resource<out T>` in `feature/auth/ui/utils/Resource.kt`:
  `Idle`, `Loading`, `Success(data)`, `Error(exception, massages)`.
- Repository and data-source calls return Kotlin `Result<T>`; the ViewModel converts that into `Resource<T>`.

## Dependency injection (Koin)

Koin is started in both platform entry points:

- Android: `shared/src/androidMain/.../MainActivity.kt`
- iOS: `shared/src/iosMain/.../MainViewController.kt`

Both load:

```kotlin
modules(authModule, commonModule, commerceModule)
```

Notable registrations:

| Type | Scope | File |
| --- | --- | --- |
| `HttpClient` | `single` | `feature/di/auth/AuthModule.kt` (created by `createHttpClient`) |
| `HomeRepository`, `HomeRemoteDataSource` | `single` | `feature/di/commerce/CommerceModule.kt` |
| `HomeTabViewModel`, `CartViewModel` | `singleOf` | `feature/di/commerce/CommerceModule.kt` |
| `ProductsViewModel` | `viewModelOf` | `feature/di/commerce/CommerceModule.kt` |
| Use cases | `factory` | `feature/di/commerce/CommerceModule.kt` |
| `DataStore<Preferences>` | platform `actual` in `commonModule` | `androidMain` / `iosMain` |

Because `HomeTabViewModel` is a singleton, Home and Categories tabs share the same loaded categories and subcategories state.

`commonModule` uses `expect`/`actual` because DataStore storage is platform-specific. The shared key is `DataStoreKeys.USER_TOKEN` in `feature/di/common/commonModule.kt`; the Android file name is `dice.preferences_pb`.

## Navigation

- `App.kt` hosts a `NavHost` with typed routes from `AppRoutes.kt`: `SplashRoute`, `LoginRoute`, `MainBottomNavRoute`, `ProductsRoute(categoryId, subCategoryId)`, `ProductDetailsRoute(productId)`.
- Start destination: `AppRoutes.SplashRoute`.
- `MainBottomNavView` keeps its own bottom-tab selection with `remember { mutableIntStateOf(0) }` and hosts Home / Categories / Wishlist / Profile tabs.
- Arguments are read with `backStackEntry.toRoute<AppRoutes.ProductsRoute>()`.

## Known architectural rough edges

See [known-issues.md](./known-issues.md) for concrete, file-level items (duplicate Koin registration, duplicate route registration, `MaterialTheme` not receiving the custom scheme, and others).
