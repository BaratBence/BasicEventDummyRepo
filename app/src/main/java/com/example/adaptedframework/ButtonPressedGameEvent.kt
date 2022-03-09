package com.example.adaptedframework

import kotlinx.parcelize.Parcelize

/**
 *  Custom class to test GameEvent's extendability
 */
@Parcelize
class ButtonPressedGameEvent(override var name: String, val pressed: Boolean) : GameEvent(name) {
    override fun getClassLoader(): ClassLoader {
        return ButtonPressedGameEvent::class.java.classLoader!!
    }
}