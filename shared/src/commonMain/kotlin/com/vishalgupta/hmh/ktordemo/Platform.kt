package com.vishalgupta.hmh.ktordemo

expect class Platform() {
    val platform: String
}

expect fun initLogger()