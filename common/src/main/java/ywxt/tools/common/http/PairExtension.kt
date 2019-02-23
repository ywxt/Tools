package ywxt.tools.common.http

import kotlinx.coroutines.withTimeout
import java.net.URL
import java.net.URLEncoder

/**
 * 这个机器人很傻逼，没什么大用。
 */
suspend fun Pair<String, String>.getReply(): String = withTimeout(200L){
    
        val str =
            "http://www.tuling123.com/openapi/api?key=2b2a13769cdb46328ee0f460de6f1eb0&info=${URLEncoder.encode(
                this@getReply.first,
                "utf-8"
            )}&userid=${this@getReply.second}"
        val url=URL(str)
        //Log.i("Message", this@getReply.second)
        url.readText()
    }
    
