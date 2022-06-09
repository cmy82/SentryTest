package com.backroomsoftwarellc.lib.sentrytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.sentry.ISpan
import io.sentry.ITransaction
import io.sentry.Sentry

class MainActivity : AppCompatActivity() {

    private var transaction : HashMap<String, ITransaction?> = HashMap()
    private val transactChildren : HashMap<String, ISpan?> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        transaction["Create"] = Sentry.startTransaction("MainActivity", "OnCreate")
        super.onCreate(savedInstanceState)
        transactChildren["CreateView"] = transaction["Create"]?.startChild("Setup View")
        setContentView(R.layout.activity_main)
        transactChildren["CreateView"]?.finish()
        transaction["Create"]?.finish()
    }
}