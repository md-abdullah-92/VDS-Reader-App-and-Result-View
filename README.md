# VDS Reader App and Result View

This is an Android application designed to scan and read VDS (Verified Digital Signature) QR codes and display the results. The app is built using modern Android development tools and libraries.

## Description

This project is an Android application for scanning, verifying, and viewing academic results secured by Visible Digital Seals (VDS). It provides a comprehensive system for students and verifiers to interact with educational records in a secure and reliable manner. The app can scan a VDS QR code on a certificate, cryptographically verify its authenticity, and display detailed semester results. It also includes features for user authentication, profile management, and even the creation and generation of VDS-secured certificates.

## Features

*   **User Management:**
    *   Secure user registration and login.
    *   Personalized user profile page.
*   **VDS & QR Code Functionality:**
    *   Fast and reliable VDS QR code scanning using the device camera.
    *   Generation of VDS for securing documents.
    *   Cryptographic verification of the digital signature within the VDS.
*   **Academic Results:**
    *   Fetch and display student semester results by providing registration details.
    *   Clear presentation of verification status (success or failure).
    *   Detailed views for full academic transcripts and individual semester results.
*   **Certificate Handling:**
    *   On-the-fly generation of digital certificates.
    *   Secure management of cryptographic key pairs for signing and verification.
*   **Modern & Secure:**
    *   Built with Jetpack Compose for a modern, responsive, and intuitive user interface.
    *   Integrates with a backend service for data handling.
    *   Includes a dedicated security page for managing keys and verification settings.

## Technologies Used

- **Kotlin:** The primary programming language for the application.
- **Jetpack Compose:** Google's modern toolkit for building native Android UI.
- **AndroidX Libraries:** A collection of libraries that are part of the Android Jetpack suite, including:
    - `core-ktx`
    - `lifecycle-runtime-ktx`
    - `activity-compose`
    - `navigation-compose`
- **CameraX/Code Scanner Library:** For camera functionalities and QR code scanning (`com.github.yuriy-budiyev:code-scanner:2.3.2`).
- **ML Kit Vision:** Google's ML Kit for barcode scanning.
- **Material Design 3:** For modern and beautiful UI components.

## Setup

To get a local copy up and running, follow these simple steps:

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your_username/VDS-Reader-App-and-Result-View-main.git
   ```
2. **Open in Android Studio:**
   - Open Android Studio.
   - Click on `File > Open` and navigate to the cloned repository's directory.
   - Let Android Studio sync the project and download the required dependencies.

3. **Build and Run:**
   - Connect an Android device or start an emulator.
   - Click the `Run` button in Android Studio to build and install the application.

## Permissions

The application requires the following permissions to function correctly:

- `android.permission.CAMERA`: To access the device's camera for scanning QR codes.
- `android.permission.INTERNET`: To access the internet if the VDS verification requires online checks.
- `android.permission.ACCESS_NETWORK_STATE`: To check the network status.
- `android.permission.WAKE_LOCK`: To keep the processor from sleeping or the screen from dimming during certain operations.
