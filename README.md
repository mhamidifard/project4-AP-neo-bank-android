# AP Neo Bank (Android)

An Android mobile banking app project (AP Neo Bank) that simulates the essential features of a neobank. Designed to demonstrate secure authentication, account management, transfers, and a clean mobile-first interface.

##  Key Features

- **Secure Authentication**: Sign in via email/password or biometric login (fingerprint/Face ID).
- **Account Overview**: View account balance, transaction history, and recent activities.
- **Money Transfers**: Support internal transfers and simulated external transfers.
- **Notifications**: Real-time transaction confirmations via Android notifications.
- **User-Friendly UI**: Intuitive interface with clean navigation and responsive layouts.
- **Local Data Persistence**: Using Room or SQLite for offline data access.

##  Tech Stack

- **Language**: Java
- **Architecture**: MVVM (Model–View–ViewModel)
- **UI**: Android Jetpack Compose or XML + View Binding
- **Local Storage**: Room Database
- **Networking**: Retrofit (if applicable)
- **Testing**: JUnit + Espresso (UI testing)
- **Dependency Management**: Gradle
- **CI/CD**: (Optional) GitHub Actions or GitLab CI

##  Getting Started

### Prerequisites

- Android Studio installed (2022.1 or newer recommended)
- Android SDK with required API levels
- Internet connection (if using remote API integrations)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/mhamidifard/project4-AP-neo-bank-android.git
   cd project4-AP-neo-bank-android
   ```

2. Open the project in Android Studio:
   ```bash
   File → Open → project4-AP-neo-bank-android
   ```

3. Sync Gradle and run the project on an emulator or physical device.

##  Project Structure

```
.
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/…         # Activities, ViewModels, Repositories
│   │   │   ├── res/…          # Layouts, Drawables, Strings
│   │   │   └── AndroidManifest.xml
│   │   └── test/…              # Unit test files
│   │   └── androidTest/…       # Instrumentation/UI tests
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
└── (Optional) CI config files
```

##  Running Tests

- **Unit Tests**:
  ```bash
  ./gradlew test
  ```

- **Instrumented UI Tests**:
  ```bash
  ./gradlew connectedAndroidTest
  ```
