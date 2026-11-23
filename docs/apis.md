# APIs

## Client configuration

File: `shared/src/commonMain/kotlin/com/route/ecommercekmp/feature/network/Api.kt`

```kotlin
fun createHttpClient(dataStore: DataStore<Preferences>): HttpClient
```

| Setting | Value |
| --- | --- |
| Base URL | `https://ecommerce.routemisr.com/api/` |
| Serialization | `ContentNegotiation` + kotlinx.serialization, `ignoreUnknownKeys = true`, `useAlternativeNames = false` |
| Timeouts | request / connect / socket = 15,000 ms |
| Logging | `Logger.SIMPLE`, `LogLevel.ALL` |
| Auth header | `token: <user_token>` added in `defaultRequest` when DataStore has a non-empty token |
| Token key | `DataStoreKeys.USER_TOKEN` (`stringPreferencesKey("user_token")`) |
| Android DataStore file | `dice.preferences_pb` |

Paths in data sources are relative to the base URL, e.g. `v1/categories`.

## Endpoints

| Method | Path | Called from | Notes |
| --- | --- | --- | --- |
| `POST` | `v1/auth/signin` | `AuthRemoteDataSourceImpl.login()` | Body `LoginRequest(email, password)`; checks `response.status.isSuccess()` manually |
| `GET` | `v1/categories` | `HomeRemoteDataSourceImpl.getCategories()` | Wrapped in `CategoriesResponse` |
| `GET` | `v1/categories/{id}/subcategories` | `HomeRemoteDataSourceImpl.getSubCategories()` | `id` must be the real `_id` from the categories response |
| `GET` | `v1/products?category=...&subcategory=...` | `HomeRemoteDataSourceImpl.getProducts()` | Query building currently has a typo — see [known-issues.md](./known-issues.md) |
| `GET` | `v1/products/{productId}` | `HomeRemoteDataSourceImpl.getProductById()` | Single product wrapper (`ProductResponse.data`); used by the Product Details screen |
| `POST` | `v1/cart` | `CartRemoteDataSourceImpl.addToCart()` | Body `AddToCartRequest(productId)`; then re-fetches the cart |
| `GET` | `v1/cart` | `CartRemoteDataSourceImpl.getCart()` | |
| `DELETE` | `v1/cart/{productId}` | `CartRemoteDataSourceImpl.removeFromCart()` | |
| `PUT` | `v1/cart/{productId}` | `CartRemoteDataSourceImpl.updateQuantity()` | Body `UpdateCartRequest(quantity)` |

## Response mapping

| DTO | JSON `data` field | Domain model | Mapper |
| --- | --- | --- | --- |
| `CategoriesResponse` (`feature/network/response/categories/`) | `data` → `List<RemoteCategory>` | `Category` | `CategoryMapper.toCategory()` |
| `ProductsResponse` (`feature/network/response/prouducts/`) | `data` → `List<RemoteProduct>` | `Product` | `ProductsMapper.toProduct()` |
| `CartResponse` (`feature/network/response/cart/`) | cart payload | `Cart` | `CartMapper` |

Both list responses also expose `metadata` and `results`.

**ID mapping is important:** the live API returns IDs as `_id`. `RemoteCategory.kt` uses `@SerialName("_id")`, which makes category and subcategory IDs available to the domain layer. `RemoteProduct.kt` still declares plain `id` — see known issues.

## Error handling

- Remote data sources wrap calls in `try/catch` and return `Result.success(...)` / `Result.failure(t)`.
- `HomeRepositoryImpl` maps `null` payloads to an empty list and preserves failures.
- `AuthRemoteDataSourceImpl` reads the error body as `AuthResponse` and uses its `message`.
- ViewModels translate `Result` into `Resource.Loading / Success / Error`.
- Ktor's default response validation throws on non-2xx statuses, so a `400` from a bad URL becomes `Result.failure` → `Resource.Error`.

## Debugging without code changes

Logging is already enabled at `LogLevel.ALL`. Run the app and:

- filter Logcat for `KtorClient` or `subcategories` to see request URLs and responses;
- use Android Studio **Network Inspector** to inspect `v1/...` calls;
- check DataStore `user_token` indirectly by looking for the `token` header in the Ktor log.

Related: [known-issues.md](./known-issues.md), [theming.md](./theming.md).
