package com.github.mariemmezghani.onboarding_presentation.welcome.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(16.dp),
        enabled = enabled
    ) {
        Text(
            text = text
        )
    }
}