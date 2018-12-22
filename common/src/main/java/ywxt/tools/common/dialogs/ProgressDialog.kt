package ywxt.tools.common.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import ywxt.tools.common.R

/**
 * Android的ProgressDialog被废弃了，只能自己实现一个简陋版本
 */
class ProgressDialog(private val mContext: Context) {
    private val builder=AlertDialog.Builder(mContext).setCancelable(false)
    private val view= LayoutInflater.from(mContext).inflate(R.layout.tools_common_dialog_progress,null)!!
    private val dialog=builder.create()
    fun show(){
        dialog.setView(view)
        dialog.show()
    }
    fun dismiss(){
        dialog.dismiss()
    }
}


