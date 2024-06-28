package com.meowsoft.opensos.data

import androidx.datastore.core.Serializer
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class Alert(
    val phoneNumber: String = "",
    val textMessage: String = "",
    val actionType: List<AlertActionType>
)

//@Suppress("BlockingMethodInNonBlockingContext")
//object AlertSerializer : Serializer<Alert> {
//    override val defaultValue: Alert
//        get() = Alert()
//
//    override suspend fun readFrom(input: InputStream): Alert {
//        return try {
//            Json.decodeFromString(
//                deserializer = Alert.serializer(),
//                string = input.readBytes().decodeToString()
//            )
//        } catch (e: SerializationException) {
//            e.printStackTrace()
//            Alert()
//        }
//    }
//
//    override suspend fun writeTo(t: Alert, output: OutputStream) =
//        output.write(
//            Json.encodeToString(
//                serializer = Alert.serializer(),
//                value = t
//            ).encodeToByteArray()
//        )
//}
