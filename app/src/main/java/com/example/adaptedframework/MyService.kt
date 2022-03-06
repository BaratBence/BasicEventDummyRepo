package com.example.adaptedframework

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class MyService : Service() {
    companion object {
        var JOB1 = 1
        var JOB2 = 2
        var JOB1Response = 3;
        var JOB2Response = 4;
    }

    var messenger = Messenger(IncomingHandler())
    override fun onBind(p0: Intent?): IBinder? {
        return messenger.binder
    }

    class IncomingHandler: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            var MSG = Message()
            var bundle = Bundle()
            when(msg.what) {
                JOB1 -> {
                    Log.e("INFO","JOB1 FROM UI")
                    var Message = "JOB1 FROM SERVICE"
                    MSG = android.os.Message.obtain(null, JOB1Response)
                    //bundle.putString("response_message", Message)
                    var test = ButtonPressedGameEvent("button",true)
                    //bundle.classLoader = test.getClassLoader()
                    bundle.putParcelable("test", test)
                    MSG.data = bundle
                    msg.replyTo.send(MSG)
                }
                JOB2 -> {
                    var Message = "JOB2 FROM SERVICE"
                    MSG = android.os.Message.obtain(null, JOB2Response)
                    bundle.putString("response_message", Message)
                    MSG.data = bundle;
                    msg.replyTo.send(MSG)
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}