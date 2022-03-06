package com.example.adaptedframework

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.adaptedframework.MyService.Companion.JOB1
import com.example.adaptedframework.MyService.Companion.JOB1Response
import com.example.adaptedframework.MyService.Companion.JOB2
import com.example.adaptedframework.MyService.Companion.JOB2Response

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var textView: TextView
    }
    var serviceConnection = MyServiceConnection(null,false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        intent = Intent(this,MyService::class.java)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)
        textView = findViewById(R.id.textView)
        var button1 = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)

        button1.setOnClickListener {
            var msg = Message.obtain(null, JOB1)
            msg.replyTo = Messenger(ResponseHandler())
            serviceConnection.messenger?.send(msg)

        }

        button2.setOnClickListener {
            var msg = Message.obtain(null, JOB2)
            msg.replyTo = Messenger(ResponseHandler())
            serviceConnection.messenger?.send(msg)

        }
    }

    override fun onStop() {
        unbindService(serviceConnection);
        super.onStop()
    }

    class ResponseHandler: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            var message = ""
            when(msg.what) {
                JOB1Response -> {
                    Log.e("INFO","JOB1 FROM PROCESS")
                    //message = msg.data.getString("response_message")!!
                    //Log.e("INFO", msg.data.classLoader.toString())
                    msg.data.classLoader = GameEvent::class.java.classLoader
                    var messageBundle = msg.data.get("test") as GameEvent
                    textView.text = messageBundle.name
                }
                JOB2Response -> {
                    message = msg.data.getString("response_message")!!
                    textView.text = message
                }
            }
            super.handleMessage(msg)
        }
    }
}