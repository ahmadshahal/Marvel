package com.kotlinhero.marvel.common.utils

import android.content.Context
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge

fun Context.enableDarkEdgeToEdge() {
    val activity = this as ComponentActivity
    activity.enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
    )
}

fun Context.enableLightEdgeToEdge() {
    val activity = this as ComponentActivity
    activity.enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
    )
}