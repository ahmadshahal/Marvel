package com.kotlinhero.marvel.network.data.env

import com.kotlinhero.marvel.common.utils.md5Hash

const val DEV_ROUTE = "https://gateway.marvel.com/v1/public"
const val PUBLIC_API_KEY = "3e149e780c3b2334bccb5ace564aabfb"
const val PRIVATE_API_KEY = "4be0f1118b4f7faf6be84594972522497df66322"

fun generateApiHash(timeStamp: String): String {
    return md5Hash(timeStamp + PRIVATE_API_KEY + PUBLIC_API_KEY)
}

fun generateTimeStamp() = System.currentTimeMillis().toString()