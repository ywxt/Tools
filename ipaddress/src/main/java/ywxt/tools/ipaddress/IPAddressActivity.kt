package ywxt.tools.ipaddress

import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ywxt.tools.common.activities.BaseActivity
import ywxt.tools.common.http.getIP

class IPAddressActivity : BaseActivity() {
    private val txtIP by lazy { findViewById<TextView>(R.id.tools_ipaddress_ipaddress) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_ipaddress_activity_ipaddress)
        enableBack = true
        showIP()
    }

    private fun showIP() = GlobalScope.launch(Dispatchers.IO) {
        val ip = getIP()
        withContext(Dispatchers.Main) {
            txtIP.text = ip ?: "发生错误"
        }
    }
}
