Passwordless Authentication App (Email + OTP)

A simple Android application demonstrating a passwordless authentication system using Email + OTP, implemented completely offline without any backend services.
The app also includes a session screen that tracks user login duration.

📌 Features

Passwordless login using Email + OTP
Local OTP generation, validation, and expiry
Maximum 3 OTP attempts per session
OTP resend with automatic reset
Explicit result handling for all OTP states
Session tracking after successful login
Rotation-safe UI using ViewModel
Clean logging using Timber

🛠 Tech Stack

Kotlin
Jetpack Compose
ViewModel + UI State
Kotlin Coroutines
Timber (Logging SDK)

🔐 Authentication Flow

User enters an email address
App generates a 6-digit OTP locally
OTP is validated with:
Expiry check (60 seconds)
Attempt limit (3 attempts)
On success, user is navigated to the session screen
User can log out to end the session

⏱ OTP Handling Logic

OTP Generation
Generated only on “Send OTP”
6-digit numeric OTP
(100000..999999).random().toString()
OTP Expiry
Valid for 60 seconds
OTP stores creation timestamp
Automatically invalidated after expiry
OTP Attempts
Maximum 3 attempts
Attempts decrease on every incorrect OTP
Validation fails when attempts reach zero
Resend OTP
Invalidates previous OTP
Resets attempts to 3
Generates a fresh OTP
Clears OTP input field

📊 OTP Validation Results

The OTP validation returns explicit states:
Success
Incorrect OTP (with remaining attempts)
Expired OTP
Attempts Exceeded
This ensures predictable UI behavior and clear error messaging.
