# Truecaller Android Assignment

This project is an Android application developed as part of a technical assignment for Truecaller. The app demonstrates knowledge of Android development, including Jetpack Compose for UI, Koin for dependency injection, and Retrofit for network requests.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [How It Works](#how-it-works)
- [Contributing](#contributing)
- [License](#license)

## Features

- Fetch and display the 15th character from a specified URL.
- Display every 15th character from the content of the URL.
- Count and display occurrences of unique words in the content.
- Dependency injection using Koin.
- Clean architecture with separate modules for API, Repository, and ViewModel.

## Architecture

The project follows the Model-View-ViewModel (MVVM) architecture pattern and utilizes Koin for dependency injection. The main components include:

- **UI Layer**: Composables for building the user interface using Jetpack Compose.
- **ViewModel**: Manages UI-related data and business logic.
- **Repository**: Abstracts data sources (API service).
- **API Layer**: Retrofit for network calls.

## Technologies Used

- **Kotlin**: Programming language for Android development.
- **Jetpack Compose**: Modern toolkit for building native UI.
- **Koin**: Dependency injection framework.
- **Retrofit**: Type-safe HTTP client for Android.
- **Coroutines**: For asynchronous programming.
- **Gradle**: Build automation tool.

## Setup Instructions

1. Clone the repository:
    ```bash
    git clone https://github.com/MayankAtulkar/TruecallerAssignment.git
    cd truecaller-assignment
    ```

2. Open the project in Android Studio.

3. Make sure to have the latest version of Android Studio and Kotlin installed.

4. Sync the Gradle files to download the necessary dependencies.

5. Run the application on an emulator or a physical device.

## Usage

- Upon launching the application, click the "Load Content" button to fetch data from the Truecaller blog.
- The application will display the 15th character, every 15th character, and the word count from the content.
- The UI is built with Jetpack Compose, providing a modern user experience.

## How It Works

1. The `MainActivity` initializes the application and sets up the user interface.
2. When the "Load Content" button is clicked, the `MainViewModel` requests data from the `MainRepository`.
3. The `MainRepositoryImpl` uses Retrofit to fetch data from the specified URL.
4. The data is processed, and the results are displayed in the UI.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request.

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Thank you for checking out this project!
