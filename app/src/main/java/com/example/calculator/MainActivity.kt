package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var lastNumaric: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val tvinput: TextView = findViewById(R.id.tvinput)
        tvinput.append((view as Button).text)
        lastNumaric = true
    }

    fun onClear(view: View) {
        val tvinput: TextView = findViewById(R.id.tvinput)
        tvinput.text = ""
        lastNumaric = false
        lastDot = false
    }

    fun onDecimal(view: View) {
        val tvinput: TextView = findViewById(R.id.tvinput)
        if (lastNumaric && !lastDot) {
            tvinput.append(".")
            lastDot = true
            lastNumaric = false
        }
    }

    fun operator(view: View){
        val tvinput: TextView = findViewById(R.id.tvinput)
        if(lastNumaric && !isOperatorAdded(tvinput.text.toString())){
            tvinput.append((view as Button).text)
            lastNumaric = false
            lastDot = false
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }
    }

    fun onEqual(view: View){
        if(lastNumaric){
            val tvinput : TextView = findViewById(R.id.tvinput)
            var prefix = ""
            var tvValue = tvinput.text.toString()
            try {

                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue  = tvValue.substring(1)


                }
                  if(tvValue.contains("-")){
                      var splitValue = tvValue.split("-")
                      var one = splitValue[0]
                      var two = splitValue[1]

                      if(!prefix.isEmpty()){
                          one = prefix + one
                      }

                      tvinput.text = (one.toDouble() - two.toDouble()).toString()
                  }else if(tvValue.contains("+")){
                      var splitValue = tvValue.split("+")
                      var one = splitValue[0]
                      var two = splitValue[1]

                      if(!prefix.isEmpty()){
                          one = prefix + one
                      }

                      tvinput.text = (one.toDouble() + two.toDouble()).toString()
                  }else if(tvValue.contains("*")){
                      var splitValue = tvValue.split("*")
                      var one = splitValue[0]
                      var two = splitValue[1]

                      if(!prefix.isEmpty()){
                          one = prefix + one
                      }

                      tvinput.text = (one.toDouble() * two.toDouble()).toString()
                  }else if(tvValue.contains("/")){
                      var splitValue = tvValue.split("/")
                      var one = splitValue[0]
                      var two = splitValue[1]

                      if(!prefix.isEmpty()){
                          one = prefix + one
                      }

                      tvinput.text = (one.toDouble() / two.toDouble()).toString()
                  }
               

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }

        }
    }



}
