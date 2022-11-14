package com.github.mariemmezghani.onboarding_presentation.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.github.mariemmezghani.core_ui.LocalSpacing


@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String)-> Unit,
    unit:String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(
        color= MaterialTheme.colors.primaryVariant,
        fontSize= 70.sp
    )

){
    val spacing= LocalSpacing.current
    Row (horizontalArrangement = Arrangement.Center){

        BasicTextField(value= value,
            onValueChange= onValueChange,
            textStyle= style,
            modifier=modifier,
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Number),
            singleLine= true)

        Spacer(modifier = Modifier.width(spacing.spaceSmall))

        Text(text = unit)

    }

}