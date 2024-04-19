package io.offscale.kotlin_multi_auth_scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginComposable(loginViewModel: SignUpViewModel = viewModel(), onNavigateToContent: () -> Unit) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(loginViewModel.successfulLogin) {
        if(loginViewModel.successfulLogin) {
            onNavigateToContent()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter email") },
            isError = loginViewModel.emailError,
            supportingText = if(loginViewModel.emailError) {
                Text(text = "Login failed")
            } else {}
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = loginViewModel.passwordError,
            supportingText = if(loginViewModel.passwordError) {
                Text(text = "Login failed")
            } else {}
        )

        Button(onClick = { loginViewModel.login(email, password) }) {
            Text(text = "LOGIN")
        }

    }
}
