package com.example.chenhaoyang.rxactivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.library.startActivityWithCallBack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.textView).setOnClickListener {
            startActivityWithCallBack(Intent(this, SecondActivity::class.java), 1) {
                Toast.makeText(this, "从 secondActivity返回，返回码是 ${it.resultCode}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
