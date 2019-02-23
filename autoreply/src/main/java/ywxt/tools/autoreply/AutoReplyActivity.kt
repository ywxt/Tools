package ywxt.tools.autoreply

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ywxt.tools.autoreply.adapters.MessageAdapter
import ywxt.tools.autoreply.data.Data
import ywxt.tools.autoreply.data.query
import ywxt.tools.autoreply.data.writeData
import ywxt.tools.autoreply.models.MessageModel
import ywxt.tools.autoreply.models.Position
import ywxt.tools.common.activities.BaseActivity
import ywxt.tools.common.dialogs.ProgressDialog
import ywxt.tools.common.message.getMessage
import ywxt.tools.common.sqlite.DBHelper

class AutoReplyActivity : BaseActivity() {


    private val btnMessageSend by lazy { findViewById<Button>(R.id.tools_autoreply_btn_message_send) }
    private val txtMessageEdit by lazy { findViewById<EditText>(R.id.tools_autoreply_txt_message_edit) }
    private val rvMessageView by lazy { findViewById<RecyclerView>(R.id.tools_autoreply_message_view) }
    private val db by lazy { DBHelper(this, DBHelper.NAME, null, DBHelper.VERSION) }
    private val dbWriter by lazy { db.writableDatabase }
    private val data = mutableListOf<MessageModel>()
    private val adapter by lazy { MessageAdapter(data) }
    private lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_autoreply_activity_autoreply)
        enableBack = true
        rvMessageView.layoutManager = LinearLayoutManager(this)
        rvMessageView.adapter = adapter
        btnMessageSend.setOnClickListener {
            sendMessage(txtMessageEdit.text)
        }
        //设置键盘回车键
        txtMessageEdit.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    btnMessageSend.callOnClick()
                    return@setOnEditorActionListener true
                }
                else -> return@setOnEditorActionListener false
            }
        }
        //创建活动时读取数据库
        val messageList = dbWriter.query()
        rvMessageView.appendRange(messageList)
        userId = Data(this).getUserId()

    }

    /**
     * 发送消息
     */
    private fun sendMessage(message: Editable?) = GlobalScope.launch (Dispatchers.Main){
        // 发送消息
        if (!message.isNullOrBlank()) {
            val dialog = ProgressDialog(this@AutoReplyActivity)
            //显示进度条，防止等的太无聊
            dialog.show()
            try {
                val reply = async(context = Dispatchers.IO) {  getMessage(Pair(message.toString(),userId))}.await()
                val messageRange = listOf(
                    MessageModel(message.toString(), Position.RIGHT),
                    MessageModel(reply, Position.LEFT)
                )
                dbWriter.writeData(messageRange)
                rvMessageView.appendRange(messageRange)
                //清空编辑框
                message.clear()
            } catch (e: TimeoutCancellationException) {
                Toast.makeText(this@AutoReplyActivity, "请求超时", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@AutoReplyActivity, "发生错误，请重试", Toast.LENGTH_SHORT).show()
                Log.e("Message", e.message + "")
            } finally {
                dialog.dismiss()
            }

        }
    }

    /**
     * 在最后面加入子项
     */
    private fun RecyclerView.appendRange(messageRange: List<MessageModel>) {
        data.addAll(messageRange)

        this@AutoReplyActivity.adapter.notifyItemRangeInserted(data.size - messageRange.size, messageRange.size)
        this.scrollToPosition(data.size - 1)
    }


}
