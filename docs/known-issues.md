# Known Issues & Rough Edges

Items below were verified by reading the repository on 2026-11-23. They are documented, not fixed here. Line numbers may drift as code changes.

## 1. Products query parameter typo

- File: `feature/commerce/data/repositories/home_repository/data_source/HomeRemoteDataSourceImpl.kt:32`
- Code:
  ```kotlin
  url += "${if (categoryId != null) "&" else ""}sub)}category=$subCategoryId"
  ```
- Expected query key: `subcategory`.
- Effect: subcategory filtering is broken when both category and subcategory IDs are supplied.
- Related: [apis.md](./apis.md).

## 2. Product IDs are probably not parsed

- File: `feature/network/response/prouducts/RemoteProduct.kt:19`
- The live API returns product IDs as `_id`, but the DTO declares plain `id` with no `@SerialName("_id")`.
- `RemoteCategory.kt` already uses `@SerialName("_id")` (fixed earlier), so category/subcategory IDs work.
- Effect: `Product.id` may be empty, which can break cart/details actions that depend on it.
- Note: changing this affects `ProductsMapper` and any code reading `Product.id`; do it as a dedicated change.

## 3. Duplicate login route registration

- File: `App.kt`, `composable<AppRoutes.LoginRoute>()` at lines 39 and 47.
- Effect: redundant destination declaration; can cause ambiguous navigation behaviour.

## 4. Custom color scheme not applied globally

- File: `App.kt:28` uses `MaterialTheme { ... }` without `colorScheme = LightColorsScheme`.
- `LightColorsScheme` is defined in `feature/Colors.kt` and used directly only by `MainBottomNavView`.
- Effect: Material 3 defaults are used for most widgets; see [theming.md](./theming.md).

## 5. Categories tab remembers a null selection forever

- File: `.../tabs/categories_tab/CategoriesTabView.kt:58-68`
- `var selectedCategory by remember { ... }` has no keys. If the Categories tab is opened while `categoryApi` is still `Loading`, `selectedCategory` starts as `null` and will not update when categories arrive.
- Effect: `LaunchedEffect(selectedCategory)` at line 70 does not dispatch `LoadSubCategories`, so the subcategories API is not called until the tab is recreated.

## 6. Duplicate Koin registration for `GetSubCategoriesUseCase`

- File: `feature/di/commerce/CommerceModule.kt`, lines 36-38 and line 40.
- Both `factory { GetSubCategoriesUseCase(get()) }` and `factoryOf(::GetSubCategoriesUseCase)` are registered.
- Effect: redundant declaration; last registration wins.

## 7. Products screen ignores navigation arguments

- Files: `ProductView.kt` passes `category` and `subCategory` into `ProductsEvents.LoadProducts`; `ProductsViewModel.kt:25` calls `productsUseCase.invoke()` with no arguments.
- Effect: product list is not filtered by category/subcategory despite the route carrying those IDs.

## 8. Naming and spelling inconsistencies

- Package `feature.network.response.prouducts` (should be `products`).
- File `AdsCarosuel.kt` (should be `AdsCarousel`).
- File `GetSubCategoryUseCase.kt` contains class `GetSubCategoriesUseCase`.
- `Resource.Error` property is named `massage`.
- See [file-naming.md](./file-naming.md) for the full list.

## 9. Placeholder KMP template files

- `Platform.kt`, `Greeting.kt`, `GreetingUtil.kt` remain at `com.route.ecommercekmp` and are unused by the app screens.
- Tests are placeholders only; see [testing.md](./testing.md).

---

When changing any of these, keep the change scoped and get approval first — this project has been maintained with minimal, file-local fixes so far.
