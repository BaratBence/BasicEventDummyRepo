package com.example.adaptedframework

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/**
 * Service class which can be binded to views to receive their messages
 */
class FrameworkCommunicationService : Service() {
    /**
     * messenger will resolve the communication between the two end
     */
    var messenger = Messenger(FrameworkCommunicationServiceIncomingHandler())

    /**
     * function to bind view and service together
     */
    override fun onBind(p0: Intent?): IBinder? {
        return messenger.binder
    }
}