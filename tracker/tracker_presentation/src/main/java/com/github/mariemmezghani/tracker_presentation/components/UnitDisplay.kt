package com.github.mariemmezghani.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.mariemmezghani.core_ui.LocalSpacing


@Composable
fun UnitDisplay(
    amount:Int,
    unit:String,
    amountTextSize:TextUnit=20.sp,
    amountColor:Color= MaterialTheme.colors.onBackground,
    unitTextSize:TextUnit= 14.sp,
    unitTextColor: Color= MaterialTheme.colors.onBackground,
    modifier: Modifier = Modifier
    ){


    val spacing= LocalSpacing.current

    Row(modifier = modifier) {

        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.h1,
            fontSize = amountTextSize,
            color = amountColor,
            modifier=Modifier.alignBy(LastBaseline)
            
        )

         Spacer(modifier=Modifier.width(spacing.spaceExtraSmall))
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.h1,
            fontSize = amountTextSize,
            color = amountColor,
            modifier=Modifier.alignBy(LastBaseline)

        )
    }

}