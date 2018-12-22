package ywxt.tools.ipaddress.http

import android.os.Handler
import ywxt.tools.common.http.sub
import java.net.URL

class IPAddressThread(private  val handler: Handler, private val id:Int):Thread(){
    private fun getIP() {
        val message = handler.obtainMessage(id)


        var txt: String?
        try {
            txt = URL("http://2018.ip138.com/ic.asp").readText(charset("gbk")).sub("<center>", "</center>")
            message.data.putString("ip", txt)
        } catch (e: Exception) {
            txt = e.message
            message.data.putString("ip", txt)

        } finally {
            message.sendToTarget()
        }
    }
    override fun run(){
        getIP()
    }
}