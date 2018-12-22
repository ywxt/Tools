package ywxt.tools.autoreply.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import ywxt.tools.autoreply.models.MessageModel
import ywxt.tools.autoreply.models.Position
import ywxt.tools.common.sqlite.DBHelper
import kotlin.random.Random

class Data(private val context: Context) {
    fun getUserId():String{
        val preference=context.getSharedPreferences("ywxt_tools_autoreply_message",Context.MODE_PRIVATE)
        //第一次运行时需要初始化一个UserId方便后面调用聊天机器人可以联系上下文
        var userId = preference.getString("userId", null)
        if (userId == null) {
            val editor = preference.edit()
            userId = Random.nextInt().toString()
            editor.putString("userId", userId)
            editor.apply()
        }
        return userId
    }
 
}

/**
 * 写入数据
 */ fun SQLiteDatabase.writeData(messageRange: List<MessageModel>) {
    val value = ContentValues()
    for (message in messageRange) {
        value.put("message_content", message.message)
        value.put("message_position", message.position.name)
        this.insert(DBHelper.NAME, null, value)
        value.clear()
    }
}

/**
 * 查询数据
 */ fun SQLiteDatabase.query(): List<MessageModel> {
    val cursor = this.query(DBHelper.NAME, null, null, null, null, null, null)
    val messageRange = mutableListOf<MessageModel>()
    if (cursor.moveToFirst()) {
        do {
            val messageContent = cursor.getString(cursor.getColumnIndex("message_content"))
            val messagePosition = Position.valueOf(cursor.getString(cursor.getColumnIndex("message_position")))
            messageRange.add(MessageModel(messageContent, messagePosition))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return messageRange
}