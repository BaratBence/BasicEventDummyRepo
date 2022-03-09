package com.example.adaptedframework

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 * The view's message handler, the handle message function will be called upon a received message and will Log
 */
class ActivityMessageHandler: Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        /**
         * setting the received message's data's classloader (to be able to recreate the Event)
         * then the data's parcelable will be read with the 'event' id and will be cast to a GameEvent
         * and its name will be logged, and will be displayed on the screen's textview.
         */
        msg.data.classLoader = GameEvent::class.java.classLoader
        var messageBundle = msg.data.get("event") as GameEvent
        Log.e("INFO","JOB1 REPLY FROM PROCESS WITH EVENT NAMED " + messageBundle.name)
        MainActivity.textView.text = messageBundle.name
    }
}