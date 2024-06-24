package com.meowsoft.opensos.ui.addalert

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.meowsoft.opensos.common.requiredPermissions

@Composable
fun AddAlertScreen(
    navHostController: NavHostController
) {
    val viewModel: AddAlertViewModel = hiltViewModel()
    val state by viewModel.addAlertUiState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // TODO confirm
                navHostController.popBackStack()
            }) {
                Icon(Icons.Filled.Done, "Confirm")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.phoneNumber,
                onValueChange = { input ->
                    viewModel.onUiEvent(AddAlertUiEvent.PhoneNumberInput(input))
                }
            )
            TextField(
                value = state.textMessage,
                onValueChange = { input ->
                    viewModel.onUiEvent(AddAlertUiEvent.MessageInput(input))
                }
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.isRingtoneActionOn,
                    onCheckedChange = { isOn ->
                        viewModel.onUiEvent(AddAlertUiEvent.ToggleRingtoneAction(isOn))
                    }
                )
                Text(
                    text = "Ringtone action",
                    fontSize = 18.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.isFlashlightActionOn,
                    onCheckedChange = { isOn ->
                        viewModel.onUiEvent(AddAlertUiEvent.ToggleFlashlightAction(isOn))
                    }
                )
                Text(
                    text = "Flashlight action",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun AddAlertScreenPreview() {
    AddAlertScreen(
        rememberNavController()
    )
}