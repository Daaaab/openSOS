package com.meowsoft.opensos.data

import androidx.datastore.core.DataStore
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlertsRepository @Inject constructor(
    private val alertsDataStore: DataStore<AlertsDataStore>
) {
    suspend fun updateAlert(updateAlert: Alert, index: Int) = alertsDataStore
        .updateData { store ->
            val alerts = store.alerts.mutate { list ->
                list[index] = updateAlert
            }
            store.copy(alerts = alerts)
        }

    suspend fun saveAlert(newAlert: Alert) = alertsDataStore
        .updateData { store ->
            val alerts = store.alerts.mutate { list ->
                list.add(newAlert)
            }
            store.copy(alerts = alerts)
        }

    fun getAlerts() = alertsDataStore
        .data
        .map { it.alerts }
}