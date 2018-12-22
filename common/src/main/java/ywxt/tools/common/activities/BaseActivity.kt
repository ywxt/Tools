package ywxt.tools.common.activities

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity(){

    var enableBack:Boolean=true
    set(value){
        val bar=supportActionBar
        bar?.setHomeButtonEnabled(value)
        bar?.setDisplayHomeAsUpEnabled(value)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}