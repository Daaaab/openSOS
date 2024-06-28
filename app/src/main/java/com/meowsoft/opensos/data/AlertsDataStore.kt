package com.meowsoft.opensos.data

import androidx.datastore.core.Serializer
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class AlertsDataStore(
    @Serializable(PersistentListSerializer::class)
    val alerts: PersistentList<Alert> = persistentListOf()
) {
    companion object {
        const val DATA_STORE_NAME = "alerts_data.json"
    }
}

@Suppress("BlockingMethodInNonBlockingContext")
object AlertsDataStoreSerializer : Serializer<AlertsDataStore> {
    override val defaultValue: AlertsDataStore
        get() = AlertsDataStore()

    override suspend fun readFrom(input: InputStream): AlertsDataStore {
        return try {
            Json.decodeFromString(
                deserializer = AlertsDataStore.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            AlertsDataStore()
        }
    }

    override suspend fun writeTo(t: AlertsDataStore, output: OutputStream) =
        output.write(
            Json.encodeToString(
                serializer = AlertsDataStore.serializer(),
                value = t
            ).encodeToByteArray()
        )
}

@OptIn(ExperimentalSerializationApi::class)
class PersistentListSerializer(
    private val serializer: KSerializer<AlertsDataStore>,
) : KSerializer<PersistentList<AlertsDataStore>> {

    private class PersistentListDescriptor :
        SerialDescriptor by serialDescriptor<List<AlertsDataStore>>() {
        @ExperimentalSerializationApi
        override val serialName: String = "kotlinx.serialization.immutable.persistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<AlertsDataStore>) {
        return ListSerializer(serializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): PersistentList<AlertsDataStore> {
        return ListSerializer(serializer).deserialize(decoder).toPersistentList()
    }

}