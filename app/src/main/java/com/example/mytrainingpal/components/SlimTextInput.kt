package com.example.mytrainingpal.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Component taken from Gabriele Mariotti's answer at
// https://stackoverflow.com/questions/67681416/jetpack-compose-decrease-height-of-textfield
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlimTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    topBottomPadding: Dp = 4.dp,
    maxChars: Int? = null
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = {
            if (maxChars == null) {
                onValueChange(it)
            } else if (it.length <= maxChars) {
                onValueChange(it)
            }
        },

        modifier = modifier,
        textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        interactionSource = interactionSource,
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            enabled = true,
            interactionSource = interactionSource,
            innerTextField = it,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            // keep horizontal paddings but change the vertical
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = topBottomPadding, bottom = topBottomPadding
            )
        )
    }
}