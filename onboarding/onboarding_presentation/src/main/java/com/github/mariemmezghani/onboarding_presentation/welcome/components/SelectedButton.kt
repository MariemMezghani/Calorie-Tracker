package com.github.mariemmezghani.onboarding_presentation.welcome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.mariemmezghani.core_ui.LocalSpacing

@Composable
fun SelectedButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    selectedTextColor: Color,
    isSelected: Boolean,
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit

) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                shape = RoundedCornerShape(100.dp),
                color = if (isSelected) color else Color.Transparent
            )
            .clickable { onClick() }
            .padding(LocalSpacing.current.spaceMedium)
    ){

        Text(
            text = text,
            style= textStyle,
            color= if (isSelected) selectedTextColor else color
        )

    }

}