package com.meowsoft.opensos.common.di

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.os.Vibrator
import android.os.VibratorManager
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.meowsoft.opensos.data.AlertsDataStore
import com.meowsoft.opensos.data.AlertsDataStoreSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun providesAlertsDataStore(context: Context): DataStore<AlertsDataStore> =
        DataStoreFactory.create(
            serializer = AlertsDataStoreSerializer,
            corruptionHandler =  ReplaceFileCorruptionHandler(
                produceNewData = { AlertsDataStore() },
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.dataStoreFile(AlertsDataStore.DATA_STORE_NAME) }
        )

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesAudioManager(context: Context) =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    @Provides
    fun providesCameraManager(context: Context) =
        context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    @Provides
    fun providesVibratorManager(context: Context) =
        context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

    @Provides
    fun providesVibrator(context: Context) =
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
}