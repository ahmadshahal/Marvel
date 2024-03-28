package com.kotlinhero.marvel.network.data.env

import com.kotlinhero.marvel.common.utils.md5Hash

const val HOST = "gateway.marvel.com"
const val PATH = "v1/public/"
const val PUBLIC_API_KEY = "3e149e780c3b2334bccb5ace564aabfb"
const val PRIVATE_API_KEY = "4be0f1118b4f7faf6be84594972522497df66322"

fun generateApiHash(timestamp: String): String {
    return md5Hash(timestamp + PRIVATE_API_KEY + PUBLIC_API_KEY)
}
