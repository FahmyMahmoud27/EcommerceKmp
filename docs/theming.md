# Theming

## Central color definitions

File: `shared/src/commonMain/kotlin/com/route/ecommercekmp/feature/Colors.kt`

```kotlin
val Blue = Color(0xFF004182)

val LightColorsScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = Blue,
    onPrimaryContainer = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
)
```

## Current usage

- `App.kt` wraps the navigation in `MaterialTheme { ... }` **without passing `LightColorsScheme`**, so Material 3 defaults are used there.
- `MainBottomNavView.kt` assigns `val colorScheme = LightColorsScheme` explicitly and uses it for `NavigationBarItemColors`.
- Many screens still use hard-coded colors instead of the central scheme.

Known hard-coded color locations:

| Color | Location |
| --- | --- |
| `Color(0xFF004182)` | `feature/auth/ui/components/CustomButton.kt`, `feature/auth/ui/screens/login/LoginView.kt` |
| `Color(0xFFF3F5F7)` | `.../tabs/categories_tab/CategoriesTabView.kt` |
| `Color(0xFFEBEBEB)`, `Color(0xFFF4F4F4)` | `feature/common/composables/ModifierExt.kt` (shimmer) |
| `Blue.copy(alpha = ...)` | `SubcategoriesGridView.kt` |

`Blue` is imported directly in several composables (for example `CategoriesGridView`, `SubcategoryCard`, `CategoriesTabView`).

## Typography

There is no central typography object. Text styling is inline via `TextStyle(...)`, mostly using `fontSize` in `sp` and occasional `FontWeight.Bold/Medium` (for example in `SubcategoryCard.kt` and `CategoriesTabView.kt`).

## Effects and resources

- Shimmer placeholder: `feature/common/composables/ModifierExt.kt` → `Modifier.shimmerEffect()`. Used by loading grids in Home and Categories.
- Images: `shared/src/commonMain/composeResources/drawable/` (`ic_cart`, `ic_plus`, `ic_star`, `ic_categories`, `ic_home`, `ic_profile`, `ic_wishlist`, `logo_route_small_blue`, ...), loaded with `painterResource(Res.drawable.<name>)`.
- Remote images: Coil `AsyncImage` in `ProductCard.kt` and `CategoriesGridView.kt`.

## Recommendations for new UI (not yet enforced)

1. Pass `colorScheme = LightColorsScheme` to `MaterialTheme` in `App.kt` if the custom palette should apply globally.
2. Replace repeated hex literals with named colors in `Colors.kt`.
3. Introduce a `Typography` object before adding more inline text styles.
4. Keep image names `ic_<name>.xml/png` for icons and descriptive names for illustrations.

These are suggestions only — changing them was out of scope when this documentation was written.
