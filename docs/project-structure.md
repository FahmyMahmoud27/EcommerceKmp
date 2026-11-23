# Project Structure

## Modules

| Module | Purpose |
| --- | --- |
| `androidApp/` | Android application entry point. Contains `MainActivity`, `AndroidManifest.xml` (declares `INTERNET` permission), and Android resources. |
| `iosApp/` | Xcode entry point for iOS. Shared UI is exported as a `Shared` framework from `shared/`. |
| `shared/` | Kotlin Multiplatform module holding almost all code and Compose UI. |
| `gradle/` | Version catalog (`libs.versions.toml`) and Gradle wrapper configuration. |

`settings.gradle.kts` includes `:androidApp` and `:shared` and enables typesafe project accessors.

## `shared/` source sets

```
shared/src/
  commonMain/
    kotlin/com/route/ecommercekmp/   # shared Kotlin + Compose code
    composeResources/drawable/       # images used via painterResource()
  androidMain/
    kotlin/...                       # MainActivity, actual Koin DataStore module, Ktor Android engine
  iosMain/
    kotlin/...                       # MainViewController, actual Koin module, Ktor Darwin engine
  commonTest/                        # shared tests (kotlin.test)
  androidHostTest/                   # Android host tests
  iosTest/                           # iOS tests
```

## Shared package layout

```
com.route.ecommercekmp
  App.kt                     # root composable + NavHost
  AppRoutes.kt               # typed navigation routes
  feature/
    auth/
      data/repositories/auth_repository/
        data_source/auth_remote_data_source/
      domain/{repositories,usecase}
      ui/{components,screens/login,utils}
    commerce/
      data/
        mappers/
        repositories/
          home_repository/{data_source}
          cart_repository/{data_source}
      domain/{model,repositories,usecase}
      ui/screen/
        cart/
        product_details/
        products/
        splash/
        main_bottom_nav_view/
          tabs/{home_tab,categories_tab,wish_list,profile}
    common/{composables,utils}
    di/{auth,commerce,common}
    network/
      request/
      response/{categories,prouducts,cart}   # includes ProductResponse for the details endpoint
  Colors.kt
  Platform.kt, Greeting.kt, GreetingUtil.kt   # KMP template leftovers
```

> Note: the package `network.response.prouducts` is misspelled in the repository. It is documented here as-is; renaming it would touch many imports and was intentionally not done.

## Where to put new code

- New feature: `feature/<feature>/{data,domain,ui}` following the existing auth/commerce split.
- Shared UI building block: `feature/common/composables/`.
- New remote model: `feature/network/response/<area>/`.
- New form body: `feature/network/request/`.
- New dependency-injection registration: `feature/di/<area>/`.
- New image: `shared/src/commonMain/composeResources/drawable/`.

See [file-naming.md](./file-naming.md) for naming details and [architecture.md](./architecture.md) for the intended data flow.
