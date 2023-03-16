package `in`.prashant.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var tvInput:TextView?=null
    var lastNumeric:Boolean=false
    var lastDot:Boolean= false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
       tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false

    }

    fun onClear(view: View){
        tvInput?.text=""
    }

    fun onDecimalPoint(view: View){
          if(lastNumeric && !lastDot){
              tvInput?.append(".")
              lastNumeric=false
              lastDot=true
          }
    }
    fun onOperator(view: View){
        tvInput?.text?.let{

            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }

    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prifix=""
            try{
                if(tvValue.startsWith("-")){
                    prifix="-"
                        tvValue=tvValue.substring(1)
                    }
                if(tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else   if(tvValue.contains("+")) {

                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }

                else   if(tvValue.contains("*")) {

                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else   if(tvValue.contains("/")) {

                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prifix.isNotEmpty()) {
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch(e:java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private  fun removeZeroAfterDot(result:String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)

        return value
    }

    private  fun  isOperatorAdded(Value:String):Boolean{
        return if(Value.startsWith("-")){
            false
        }else{
            (Value.contains("/")||Value.contains("*")||Value.contains("+")||Value.contains("-"))
            false
        }
    }
}