package com.meowsoft.sosbutton.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.meowsoft.sosbutton.R
import com.meowsoft.sosbutton.databinding.ActivityMainBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sub = RxPermissions(this@MainActivity)
            .request(
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.ACCESS_NOTIFICATION_POLICY
            )
            .subscribeBy(
                {
                val x = 0
                },{
                    val x = 0
                },{
                    val x = 0
                }
            )

        disposable.add(sub)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(
                this@MainActivity,
                R.layout.activity_main
            )
            .also {
                it.viewModel = viewModel
                it.lifecycleOwner = this@MainActivity
            }
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.dispose()
    }
}