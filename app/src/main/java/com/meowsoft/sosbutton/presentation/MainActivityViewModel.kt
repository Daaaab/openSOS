package com.meowsoft.sosbutton.presentation

import android.telephony.SmsManager
import android.view.View
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel() {

    fun onSosClicked(view: View){
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(
            "508143841",
            null,
            "test msg",
            null,
            null
        )
    }
}