package com.example.chenhaoyang.rxactivity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library.startActivityWithCallBack

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startActivityWithCallBack(
                Intent(activity, SecondActivity::class.java), 1) {

        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}