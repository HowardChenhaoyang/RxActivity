package com.example.chenhaoyang.rxactivity

import android.app.Fragment
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.library.startActivityWithCallBack

class MainAppFragment:Fragment(){
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startActivityWithCallBack(
                Intent(activity, SecondActivity::class.java), 1) {
                Toast.makeText(activity,it.resultCode.toString(),Toast.LENGTH_LONG).show()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}