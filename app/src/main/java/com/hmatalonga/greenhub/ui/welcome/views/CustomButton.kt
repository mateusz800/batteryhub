package com.hmatalonga.greenhub.ui.welcome.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    highlighted: Boolean = false,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (highlighted) MaterialTheme.colors.primary else Color.Transparent,
            contentColor = if (highlighted) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
    ) {
        content()
    }
}