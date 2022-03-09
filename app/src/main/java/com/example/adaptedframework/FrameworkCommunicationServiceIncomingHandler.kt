package com.example.adaptedframework

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 * FrameworkCommunicator's message handler
 */
class FrameworkCommunicationServiceIncomingHandler: Handler(Looper.getMainLooper()) {
    /**
     * function which will receive the sent messages and if the message was sent by JOB1 then
     * replies to it, in JOB2-s case it won't else calls the default implementation. In both
     * cases will log the caller and the message (button's text)
     */
    override fun handleMessage(msg: Message) {
        when(msg.what) {
            Constants.Job1ButtonMessageID -> {
                /**
                 * setting the received message's data's classloader (to be able to recreate the Event)
                 * then the data's parcelable will be read with the 'event' id and will be cast to a GameEvent
                 * and its name will be logged
                 */
                msg.data.classLoader = GameEvent::class.java.classLoader
                var messageBundle = msg.data.get("event") as GameEvent
                Log.e("INFO","JOB1 FROM UI WITH EVENT NAMED: " + messageBundle.name)

                /**
                 *  Creating a reply message without a header and with the Job1Button ID, and setting the
                 *  payload to the received message's
                 */
                var replyMessage = Message.obtain(null, Constants.Job1ButtonMessageID)
                replyMessage.data = msg.data
                msg.replyTo.send(replyMessage)
            }
            Constants.Job2ButtonMessageID -> {
                msg.data.classLoader = GameEvent::class.java.classLoader
                var messageBundle = msg.data.get("event") as GameEvent
                Log.e("INFO","JOB1 FROM UI WITH EVENT NAMED: " + messageBundle.name)
            }
            else -> super.handleMessage(msg)
        }
    }
}