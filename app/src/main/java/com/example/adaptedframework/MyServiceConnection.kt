package com.example.adaptedframework

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Messenger

class MyServiceConnection(messenger: Messenger?, isBind: Boolean): ServiceConnection {
    var messenger: Messenger?=null
    var isBind = false

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        messenger = Messenger(p1)
        isBind = true
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        messenger = null
        isBind = false
    }
}