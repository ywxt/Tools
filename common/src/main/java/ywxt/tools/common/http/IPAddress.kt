package ywxt.tools.common.http

import kotlinx.coroutines.withTimeoutOrNull
import java.net.URL

suspend fun getIP() = withTimeoutOrNull(1500L) {
    try {
        URL("http://2018.ip138.com/ic.asp").readText(charset("gbk")).middleIn("<center>", "</center>")
    } catch (e: Exception) {
        e.message 
    }
}