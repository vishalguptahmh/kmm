package com.vishalgupta.hmh.ktordemo.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vishalgupta.hmh.ktordemo.Greeting
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

//fun greet(): String {
//    return Greeting().greeting()
//}

class MainActivity : AppCompatActivity() {
    private  val greeting = Greeting()
    private  val mainScrope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading.."

        mainScrope.launch {
            kotlin.runCatching {
                greeting.greeting()
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = "failer : ${it.localizedMessage}"
            }
        }
    }
}
