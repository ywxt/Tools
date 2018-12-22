package ywxt.tools.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import ywxt.tools.common.R

/**
 * TODO 使EditText可编辑，暂缓
 */
class NumberBar(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val txtNumber by lazy { findViewById<EditText>(R.id.txt_number) }
    private val btnPlus by lazy {  findViewById<Button>(R.id.btn_plus) }
    private val btnLess by lazy {  findViewById<Button>(R.id.btn_less) }
    var currentValue = 0
        set(value) {
            when (value) {
                maxVaule -> {
                    btnPlus.isEnabled=false
                    txtNumber.setText(value.toString())
                    field=value
                }
                minValue -> {
                    btnLess.isEnabled=false
                    txtNumber.setText(value.toString())
                    field=value
                }
                in (minValue + 1)..(maxVaule - 1) -> {
                    btnLess.isEnabled=true
                    btnPlus.isEnabled=true
                    txtNumber.setText(value.toString())
                    field=value
                }
                else -> throw Exception("NumberBar范围超出")
            }
        }
    var minValue = 0
    var maxVaule=100
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.tools_common_layout_umber_bar, this)
        txtNumber.isEnabled=false
        if (attrs!=null){
            val array=context.theme.obtainStyledAttributes(attrs,R.styleable.NumberBar,0,0)
            minValue=array.getInteger(R.styleable.NumberBar_minValue,0)
            maxVaule=array.getInteger(R.styleable.NumberBar_maxValue,100)
            currentValue=array.getInteger(R.styleable.NumberBar_value,0)
            array.recycle()
        }
        btnPlus.setOnClickListener( this::onPlusButtonClick)
        btnLess.setOnClickListener(this::onLessButtonClick)
    }

    private fun onLessButtonClick(v: View?){
        if (btnLess.isEnabled){
            currentValue--
        }
    }
    private fun onPlusButtonClick(v:View){
        if (btnPlus.isEnabled){
            currentValue++
        }
       
    }

}