package com.meowsoft.opensos.data

import androidx.datastore.core.DataStore
import com.meowsoft.opensos.domain.model.Alert
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlertsRepository @Inject constructor(
    private val alertsDataStore: DataStore<AlertsDataStore>
) {
    suspend fun clearAlerts() = mutateAlerts { list ->
        list.clear()
    }

    suspend fun updateAlert(updateAlert: Alert, index: Int) = mutateAlerts { list ->
        list[index] = updateAlert
    }

    suspend fun saveAlert(newAlert: Alert) = mutateAlerts { list ->
        list.add(newAlert)
    }

    fun getAlerts() = alertsDataStore.data.map { it.alerts.toList() }

    private suspend fun mutateAlerts(mutator: (MutableList<Alert>) -> Unit) = alertsDataStore
        .updateData { store ->
            val alerts = store.alerts.mutate(mutator)
            store.copy(alerts = alerts)
        }
}
