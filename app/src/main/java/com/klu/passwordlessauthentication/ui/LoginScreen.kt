package com.klu.passwordlessauthentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    onSendOtpClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    val isValidGmail = email.endsWith("@gmail.com") && email.length > "@gmail.com".length

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Login", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                onEmailChange(newValue.trim())
            },
            label = { Text("Email") },
            singleLine = true,
            isError = email.isNotEmpty() && !isValidGmail,
            modifier = Modifier.fillMaxWidth()
        )


        if (email.isNotEmpty() && !isValidGmail) {
            Text(
                text = "Please enter a valid Gmail address (example@gmail.com)",
                color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSendOtpClick,
            enabled = isValidGmail,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }
    }
}


