package com.example.adaptedframework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

abstract class GameEvent(open var name: String) : Parcelable {
    abstract fun getClassLoader(): ClassLoader
}