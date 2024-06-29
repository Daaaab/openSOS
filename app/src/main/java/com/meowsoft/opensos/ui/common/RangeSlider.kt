@file:OptIn(ExperimentalMaterial3Api::class)

package com.meowsoft.opensos.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RangeSlider(
    modifier: Modifier = Modifier,
    state: SliderState,
    label: String,
    unit: String = ""
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = label,
                fontSize = 18.sp
            )
            Text(
                text = "${state.value.toInt()}$unit",
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.valueRange.start.toInt().toString())
            Slider(
                modifier = Modifier.weight(1f),
                state = state,
            )

            Text(text = state.valueRange.endInclusive.toInt().toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RangeSliderPreview() {
    RangeSlider(
        state = SliderState(value = 5F, steps = 10, valueRange = 0F..10F),
        label = "Duration:",
        unit = "s"
    )
}