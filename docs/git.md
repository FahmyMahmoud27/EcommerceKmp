# Git Workflow

## Current setup

- Main branch: `master`
- Remote: `origin`
- Local and remote feature branches observed: `feature/login`, `feature/home_tab`, `feature/categories`, `feature/products`, `feature/cart`
- `.gitignore` excludes: `*.iml`, `.kotlin`, `.gradle`, `**/build/`, `.idea`, `local.properties`, `xcuserdata`, `.DS_Store`, `captures`, native build folders, Xcode user data, `node_modules/`.

## Branch naming

Existing pattern:

```
feature/<short-feature-name>       # feature/login, feature/home_tab
```

Suggested additions for new work (not currently in history):

```
fix/<short-description>
docs/<short-description>
refactor/<short-description>
chore/<short-description>
```

Use lowercase and underscores or hyphens consistently; keep the name short and descriptive.

## Commit message style

Recent history:

```
fb535f8 implement products screen UI
6b1bbfe implement categories screen UI
d75919f implement home screen UI
1fb2b4a implement product, category, and home data layers
fccc675 Add Main bottom nav design with home tab view
ea61a73 Save user token in data store while opening login only if token is empty
461140c Initial commit
```

The existing history uses short free-form summaries with mixed capitalisation. A stricter convention for new commits:

```
<type>: <imperative summary>

feat: add category selection to home tab
fix: map category _id from categories response
docs: add project documentation set
refactor: split home tab state and events
test: cover category mapper
```

Types: `feat`, `fix`, `docs`, `refactor`, `test`, `chore`.

## Suggested workflow

1. Start from an up-to-date `master`: `git checkout master && git pull`.
2. Create a topic branch: `git checkout -b feature/<name>`.
3. Make small, focused commits. Do not mix unrelated changes.
4. Before pushing: build, run available tests, and inspect `git status` / `git diff`.
5. Push and open a pull request into `master`.
6. Keep secrets out of the repository: `local.properties` is ignored; never commit tokens or credentials.

## Pre-commit checklist

- [ ] `./gradlew.bat :androidApp:assembleDebug` succeeds (Windows) or `./gradlew :androidApp:assembleDebug` (macOS/Linux).
- [ ] `./gradlew.bat :shared:testAndroidHostTest` passes.
- [ ] No generated `build/`, `.gradle/`, or IDE files are staged.
- [ ] No new hard-coded API base URL or token.
- [ ] Docs updated if behaviour, endpoints, or naming changed.
- [ ] Diff contains only the intended files.

Related: [build-and-run.md](./build-and-run.md), [testing.md](./testing.md), [file-naming.md](./file-naming.md).
