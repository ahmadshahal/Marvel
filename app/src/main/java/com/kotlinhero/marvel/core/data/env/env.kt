package com.kotlinhero.marvel.core.data.env

const val HOST = "gateway.marvel.com"
const val PATH = "v1/public/"

// * API_KEYS should be securely saved in uncommitted to git files
// * eg. local.properties, but of the sake of this test and the requirement
// * that it should run out of the box they were written publicly like this..
// * Reference: https://www.youtube.com/watch?v=-2ckvIzs0nU
const val PUBLIC_API_KEY = "3e149e780c3b2334bccb5ace564aabfb"
const val PRIVATE_API_KEY = "4be0f1118b4f7faf6be84594972522497df66322"