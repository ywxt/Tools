package ywxt.tools.ipaddress

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import ywxt.tools.ipaddress.http.IPAddressThread
import ywxt.tools.common.activities.BaseActivity
import kotlin.random.Random

class IPAddressActivity : BaseActivity() {
    private val txtIP by lazy { findViewById<TextView>(R.id.tools_ipaddress_ipaddress) }
    private val mId= Random.nextInt()
    private val handler= Handler{
        if (it.what==mId){
            txtIP?.text=it.data.getString("ip")
            return@Handler true
        }
        false
    }
    private val thread = IPAddressThread(handler,mId)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_ipaddress_activity_ipaddress)
        enableBack=true
        thread.start()
    }
}
