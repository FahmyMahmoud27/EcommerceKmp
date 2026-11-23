# File Naming & Code Style

This page records the naming that exists today and a suggested convention for new files. Existing files were **not** renamed when this documentation was written.

## Observed conventions

| Kind | Pattern | Examples |
| --- | --- | --- |
| Screen composable | `<Area>View.kt` or `<Area>TabView.kt` | `HomeTabView.kt`, `ProductView.kt`, `CategoriesTabView.kt`, `SplashView.kt`, `LoginView.kt`, `WishListTabView.kt` |
| ViewModel | `<Area>ViewModel.kt` | `HomeTabViewModel.kt`, `ProductsViewModel.kt`, `LoginViewModel.kt`, `CartViewModel.kt` |
| State + events | `<Area>State.kt` | `HomeTabState.kt`, `ProductsState.kt`, `CartState.kt`, `LoginStates.kt` |
| Reusable composable | Descriptive name | `ProductCard.kt`, `SubcategoryCard.kt`, `CategoriesGridView.kt`, `ProductsGridView.kt`, `AdsCarosuel.kt`, `SearchTextField.kt` |
| Domain model | Singular noun | `Category.kt`, `Product.kt`, `Cart.kt` |
| Use case | `<Verb><Noun>UseCase.kt` | `GetCategoriesUseCase.kt`, `GetProductsUseCase.kt`, `GetSubCategoryUseCase.kt` |
| Repository interface | `<Noun>Repository.kt` | `HomeRepository.kt`, `CartRepository.kt`, `AuthRepository.kt` |
| Repository implementation | `<Noun>RepositoryImpl.kt` | `HomeRepositoryImpl.kt`, `CartRepositoryImpl.kt`, `AuthRepositoryImpl.kt` |
| Remote data source | `<Noun>RemoteDataSource.kt` / `...Impl.kt` | `HomeRemoteDataSource.kt`, `HomeRemoteDataSourceImpl.kt` |
| Request DTO | `<Action>Request.kt` | `LoginRequest.kt`, `AddToCartRequest.kt`, `UpdateCartRequest.kt` |
| Response DTO | `<Noun>Response.kt` / `Remote<Noun>.kt` | `CategoriesResponse.kt`, `RemoteCategory.kt`, `ProductsResponse.kt`, `RemoteProduct.kt` |
| Mapper | `<Noun>Mapper.kt` with `to<Noun>()` extension | `CategoryMapper.kt`, `ProductsMapper.kt`, `CartMapper.kt` |
| DI module | `<Area>Module.kt` | `AuthModule.kt`, `CommerceModule.kt`, `commonModule.kt` |
| Resource value | `ic_<name>` for icons | `ic_cart`, `ic_plus`, `ic_star`, `logo_route_small_blue` |

Package names are lowercase and follow the directory path, e.g.
`com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab`.

## Inconsistencies to be aware of

- `View` vs `TabView` vs `View` suffixes are mixed (`HomeTabView`, `CategoriesTabView`, `ProductView`).
- `HomeTabState.kt` holds state **and** events; `ProductsState.kt` does the same; `LoginStates.kt` is plural.
- `GetSubCategoryUseCase.kt` contains the class `GetSubCategoriesUseCase` (file singular, class plural).
- `commonModule.kt` starts lowercase while `AuthModule.kt` / `CommerceModule.kt` start uppercase.
- Package `feature.network.response.prouducts` is misspelled (`prouducts`).
- `AdsCarosuel.kt` is misspelled (`Carosuel`).
- `LoginStates.kt` vs `ProductsState.kt` plural/singular mismatch.
- `Resource.Error` has the misspelled property `massage`.

## Suggested convention for new code

1. **Files:** PascalCase, one public type per file, file name exactly matching the primary type.
2. **Screens:** `<Feature>Screen.kt` (or keep `<Feature>View.kt` for consistency with the current majority).
3. **State:** `<Feature>State.kt` containing `<Feature>State` and `<Feature>Event(s)`; prefer singular `Event`.
4. **Use cases:** file and class must match, e.g. `GetSubCategoriesUseCase.kt` → `GetSubCategoriesUseCase`.
5. **DTOs:** prefix remote entities with `Remote`, response wrappers with `<Noun>Response`, request bodies with `<Action>Request`.
6. **Icons:** `ic_<snake_case>` in `composeResources/drawable`.
7. **DI modules:** PascalCase (`CommonModule.kt`) for new modules.
8. Do not introduce new misspellings; fix an existing one only in a dedicated, approved change because it affects imports.

## Import/style notes

- Kotlin official style: 4-space indent, no wildcard imports.
- Composables are PascalCase functions; private helpers lowercase.
- ViewModels expose `val state: MutableStateFlow<...>` and a single `doAction(event)` entry point in the current code.

See [project-structure.md](./project-structure.md) for where new files belong and [git.md](./git.md) for commit hygiene.
