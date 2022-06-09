package com.backroomsoftwarellc.lib.sentrytest

import android.app.Application
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid
import io.sentry.android.fragment.FragmentLifecycleIntegration

class SentryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        SentryAndroid.init(this) { options ->
            with(options) {
                dsn = "https://9f50c0a42ad04f42b03aa97bdb16aa3a@sentry.io/5184096"
                isEnableNdk = false
                release = "test-release"
                isDebug = true

                //screenshot not available yet for android
                // Possibly use to gather user feedback on errors/crashes
                beforeSend = SentryOptions.BeforeSendCallback { event, hint ->
                    event
                }

                isEnableAutoSessionTracking = true
                options.tracesSampleRate = 1.0
                //options.isTraceSampling = true
                //options.isEnableAutoActivityLifecycleTracing = true
                //options.isEnableActivityLifecycleTracingAutoFinish = true
                //options.isEnableUserInteractionBreadcrumbs = true
                //addIntegration(ActivityLifecycleIntegration(this@GEBApp))
                addIntegration(FragmentLifecycleIntegration(this@SentryApp, enableAutoFragmentLifecycleTracing = true, enableFragmentLifecycleBreadcrumbs = true))

            }
        }

    }

}