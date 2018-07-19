package com.example.chenhaoyang.rxactivity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<View>(R.id.textView).setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
