package ywxt.tools.common.message

import org.json.JSONObject
import ywxt.tools.common.http.getReply

suspend fun getMessage(info: Pair<String, String>):String {

    val result = info.getReply()
    val json = JSONObject(result)
    val code = json.getInt("code")
    return when (code) {
        100000 -> json["text"].toString()
        40002 -> json["text"].toString()
        200000 -> "${json["text"]}\n${json["url"]}"
        else -> "发生错误，错误代码:$code"
    }

}