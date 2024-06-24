package com.meowsoft.opensos.common.di

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.os.Vibrator
import android.os.VibratorManager
import com.meowsoft.opensos.common.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

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

    @Provides
    fun providesSettings(context: Context) = Settings(context)
}