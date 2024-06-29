package com.meowsoft.opensos.ui.addalert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.meowsoft.opensos.ui.common.RangeSlider
import com.meowsoft.opensos.ui.common.VSPacer
import com.meowsoft.opensos.ui.theme.dimen16

@Composable
fun AddAlertScreen(
    viewModel: AddAlertViewModel
) {
    val state by viewModel.addAlertUiState.collectAsStateWithLifecycle()


    AddAlertLayout(
        state,
        viewModel::onUiEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddAlertLayout(
    state: AddAlertUiState,
    onUiEvent: (AddAlertUiEvent) -> Unit
) {
    val durationSliderState = remember {
        SliderState(value = 10F, steps = 30, valueRange = 0f..30f)
    }

    val volumeSliderState = remember {
        SliderState(value = 30F, steps = 100, valueRange = 0f..100f)
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onUiEvent(AddAlertUiEvent.ConfirmClicked(
                    duration = durationSliderState.value.toInt(),
                    volume = volumeSliderState.value.toInt()
                ))
            }) {
                Icon(Icons.Filled.Done, "Confirm")
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .padding(dimen16)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Phone number",
                    fontSize = 18.sp
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.phoneNumber,
                    onValueChange = { input ->
                        onUiEvent(AddAlertUiEvent.PhoneNumberInput(input))
                    }
                )
                VSPacer()
                Text(
                    text = "Message",
                    fontSize = 18.sp
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.textMessage,
                    onValueChange = { input ->
                        onUiEvent(AddAlertUiEvent.MessageInput(input))
                    }
                )
                VSPacer()
                RangeSlider(
                    modifier = Modifier.fillMaxWidth(),
                    state = durationSliderState,
                    label = "Duration:",
                    unit = "s"
                )
                Text(
                    text = "Ringtone",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.isRingtoneActionOn,
                        onCheckedChange = { isOn ->
                            onUiEvent(AddAlertUiEvent.ToggleRingtoneAction(isOn))
                        }
                    )
                    Text(
                        text = "Enabled",
                        fontSize = 18.sp
                    )
                }
                RangeSlider(
                    modifier = Modifier.fillMaxWidth(),
                    state = volumeSliderState,
                    label = "Volume:",
                    unit = "%",
                    enabled = state.isRingtoneActionOn
                )
                Text(
                    text = "Flashlight",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.isFlashlightActionOn,
                        onCheckedChange = { isOn ->
                            onUiEvent(AddAlertUiEvent.ToggleFlashlightAction(isOn))
                        }
                    )
                    Text(
                        text = "Enabled",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun AddAlertScreenPreview() {
    AddAlertLayout(
        AddAlertUiState(),
    ) {}
}