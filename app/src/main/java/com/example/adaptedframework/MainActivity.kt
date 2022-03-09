package com.example.adaptedframework

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

/**
 * main activity containing 2 button's which sends ButtonPressedGameEvent to the remote process
 */
class MainActivity : AppCompatActivity() {
    /**
     * Dummy textview variable (should not be global)
     */
    companion object {
        lateinit var textView: TextView
    }

    /**
     * The view's connection to the service (default state does not have a messenger yet since it is not binded)
     */
    var serviceConnection = ServiceConnector(null,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        /**
         *  binding the service with the created serviceConnector
         */
        intent = Intent(this,FrameworkCommunicationService::class.java)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)

        textView = findViewById(R.id.textView)
        var button1 = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)

        /**
         * Creating a message with the Job1ButtonMessageID, and creating a basic ButtonPressedGameEvent.
         * Then putting the event into a bundle which will the message's data. In the end setting the message's replyTo
         * value to the view's message handler via the messenger so the message will be handled by it after return,
         * then sending the message via the messenger.
         */
        button1.setOnClickListener {
            var msg = Message.obtain(null, Constants.Job1ButtonMessageID)
            var bundle = Bundle()
            var event = ButtonPressedGameEvent("ButtonPressedGameEvent",true)
            bundle.putParcelable("event", event)
            msg.data = bundle
            msg.replyTo = Messenger(ActivityMessageHandler())
            serviceConnection.messenger?.send(msg)
        }

        button2.setOnClickListener {
            var msg = Message.obtain(null, Constants.Job2ButtonMessageID)
            var bundle = Bundle()
            var event = ButtonPressedGameEvent("ButtonPressedGameEvent",true)
            bundle.putParcelable("event", event)
            msg.data = bundle
            serviceConnection.messenger?.send(msg)
        }
    }

    /**
     * upon changing view it is necessary to unbind the service
     */
    override fun onStop() {
        unbindService(serviceConnection);
        super.onStop()
    }
}