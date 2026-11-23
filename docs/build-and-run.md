# Build & Run

## Tooling

Versions come from `gradle/libs.versions.toml` (reviewed 2026-11-23):

| Item | Version |
| --- | --- |
| Kotlin | 2.3.20 |
| Android Gradle Plugin | 8.13.1 |
| Compose Multiplatform | 1.7.3 |
| Ktor | 3.4.1 |
| Koin | 4.1.1 |
| Navigation Compose | 2.8.0-alpha10 |
| Coroutines | 1.10.2 |
| compileSdk / targetSdk | 36 |
| minSdk | 24 |
| Android JVM target | JVM 11 |
| Shared namespace | `com.route.ecommercekmp.shared` |

Use the Gradle wrapper; no separate Gradle installation is required.

## Android

```powershell
# Windows
.\gradlew.bat :androidApp:assembleDebug
```

```bash
# macOS / Linux
./gradlew :androidApp:assembleDebug
```

You can also run the `androidApp` configuration from the IDE run widget. The app declares `android.permission.INTERNET` in `androidApp/src/main/AndroidManifest.xml`.

## iOS

Open `iosApp/` in Xcode and run the app from there. The shared module builds a static framework named `Shared` for `iosArm64` and `iosSimulatorArm64` (configured in `shared/build.gradle.kts`).

## Tests

```powershell
.\gradlew.bat :shared:testAndroidHostTest
.\gradlew.bat :shared:iosSimulatorArm64Test
```

See [testing.md](./testing.md) for the current test files.

## Useful commands

```powershell
.\gradlew.bat tasks                 # list all available tasks
.\gradlew.bat clean                 # remove build outputs
.\gradlew.bat :shared:build         # build the shared module
```

## Networking prerequisites

- Device/emulator needs internet access to `https://ecommerce.routemisr.com/api/`.
- The app reads/writes the auth token in DataStore; on Android the file is `dice.preferences_pb` in the app files directory.
- Ktor logging is already on (`LogLevel.ALL`) — see [apis.md](./apis.md) for debugging.

Related: [project-structure.md](./project-structure.md), [git.md](./git.md).
