package com.meowsoft.opensos.ui.alertslist

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.FlashlightOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.meowsoft.opensos.common.requiredPermissions
import com.meowsoft.opensos.domain.model.Alert
import com.meowsoft.opensos.domain.model.AlertActionType
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AlertsListScreen(
    viewModel: AlertsListViewModel
) {
    val permissionState = rememberMultiplePermissionsState(permissions = requiredPermissions)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGrantedMap ->
        isGrantedMap.forEach { (permission, isGranted) ->
            if (isGranted) {
                // Permission granted
            } else {
                // Handle permission denial
                val x = 0
            }
        }
    }

    LaunchedEffect(permissionState) {
        permissionState.permissions.forEach { permission ->
            if(!permission.status.isGranted && permission.status.shouldShowRationale) {
                // TODO show rationale
                val x = 0
            } else {
                requestPermissionLauncher.launch(requiredPermissions.toTypedArray())
            }
        }
    }

    val alertsState by viewModel.alerts.collectAsStateWithLifecycle(persistentListOf())

    MainScreenLayoutPermissionsGranted(
        alertsState,
        onNavigateToAddAlerts = viewModel::navigateToAddAlert
    ){}
}

@Composable
fun MainScreenLayoutPermissionsGranted(
    alertsList: List<Alert>,
    onNavigateToAddAlerts: () -> Unit,
    onButtonClick: () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToAddAlerts()
            }) {
                Icon(Icons.Filled.Add, "Add")
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
           alertsList
               .forEachIndexed {index, alert ->
                   Row {
                       Text(text = "$index.")
                       Column {
                           Text(text = alert.textMessage)
                           Text(text = alert.phoneNumber)
                       }
                       if(alert.actionTypes.contains(AlertActionType.RINGTONE)) {
                           Icon(
                               imageVector = Icons.Rounded.Notifications,
                               contentDescription = ""
                           )
                       }
                       if(alert.actionTypes.contains(AlertActionType.FLASHLIGHT)) {
                           Icon(
                               imageVector = Icons.Rounded.FlashlightOn,
                               contentDescription = ""
                           )
                       }
                   }
               }
        }
    }
}
