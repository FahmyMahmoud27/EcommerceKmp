# Testing

## Current tests

| File | Content |
| --- | --- |
| `shared/src/commonTest/kotlin/com/route/ecommercekmp/SharedCommonTest.kt` | Placeholder `assertEquals(3, 1 + 2)` |
| `shared/src/androidHostTest/kotlin/com/route/ecommercekmp/SharedLogicAndroidHostTest.kt` | Platform placeholder |
| `shared/src/iosTest/kotlin/com/route/ecommercekmp/SharedLogicIOSTest.kt` | Platform placeholder |

There are currently no tests for mappers, use cases, repositories, ViewModels, or URL building.

## Running tests

From the repository root:

```powershell
.\gradlew.bat :shared:testAndroidHostTest
.\gradlew.bat :shared:iosSimulatorArm64Test
```

In the IDE, use the run button in the editor gutter next to a `@Test` function.

## Recommended coverage (not added yet)

1. **DTO mapping** — verify `RemoteCategory` reads `_id`, and `CategoryMapper.toCategory()` keeps `name`, `image`, `category`, and `id`.
2. **URL building** — verify `v1/products` query string uses `category` and `subcategory` (see [known-issues.md](./known-issues.md)).
3. **Repository mapping** — `Result.success` with `null` payload → empty list; failures are propagated.
4. **ViewModels** — event dispatch updates `Resource.Loading` → `Success` / `Error` in `HomeTabState` and `ProductsState`.
5. **Navigation arguments** — `ProductsRoute` receives real category and subcategory IDs.

Suggested structure: keep shared unit tests in `commonTest` so they run on every target; put platform-specific tests in `androidHostTest` / `iosTest` only when they need platform APIs.

Related: [build-and-run.md](./build-and-run.md), [apis.md](./apis.md).
