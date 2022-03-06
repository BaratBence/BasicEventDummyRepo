package com.example.adaptedframework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ButtonPressedGameEvent(override var name: String, val pressed: Boolean) : GameEvent(name) {
    override fun getClassLoader(): ClassLoader {
        return ButtonPressedGameEvent::class.java.classLoader
    }
}