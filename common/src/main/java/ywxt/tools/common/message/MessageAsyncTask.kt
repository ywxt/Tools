package ywxt.tools.common.message

import android.os.AsyncTask
import org.json.JSONObject
import ywxt.tools.common.http.httpGet
import java.lang.Exception

class MessageAsyncTask(val onCompleted:(result:String)->Unit) : AsyncTask<String, Int, String>() {
    
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to [.execute]
     * by the caller of this task.
     *
     * This method can call [.publishProgress] to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     *
     * @return A result, defined by the subclass of this task.
     *
     * @see .onPreExecute
     * @see .onPostExecute
     *
     * @see .publishProgress
     */
    override fun doInBackground(vararg params: String?): String {
        return try{
            arrayOf(params[0]!!,params[1]!!).httpGet()
        }catch (e:Exception){
            e.message+""
        }
    }

    /**
     * 完成之后
     */
    override fun onPostExecute(result: String?) {
        if (result!=null) {
            val json=JSONObject(result)
            val code=json.getInt("code")
            val message=when(code){
                100000->json["text"].toString()
                40002->json["text"].toString()
                200000->"${json["text"]}\n${json["url"]}"
                else->"发生错误，错误代码:$code"
            }
            onCompleted(message)
            
        }
    }
    
    
}