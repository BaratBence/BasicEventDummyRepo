package com.example.adaptedframework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * abstract class, describing every event that can occur while playing games
 * every new event should extend this GameEvent, and use the @Parcelable annotation
 */
abstract class GameEvent(open var name: String) : Parcelable {
    abstract fun getClassLoader(): ClassLoader
}