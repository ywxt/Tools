package ywxt.tools.common.http

import android.util.Log
import java.net.URL
import java.net.URLEncoder
/**
 * 这个机器人很傻逼，没什么大用。
 */
fun Array<String>.httpGet(): String {
    val str = "http://www.tuling123.com/openapi/api?key=2b2a13769cdb46328ee0f460de6f1eb0&info=${URLEncoder.encode(this[0], "utf-8")}&userid=${this[1]}"
    val url = URL(str)
    Log.i("Message",this[1])
    return url.readText()

}
fun String.sub(start:String,end:String):String{
    val s=this.indexOf(start)
    val e=this.indexOf(end,s+1)
    return this.substring((s+start.length)..(e-1))
}