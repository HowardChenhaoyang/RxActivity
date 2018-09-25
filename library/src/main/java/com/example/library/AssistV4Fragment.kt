package com.example.library

import android.content.Intent
import android.support.v4.app.Fragment

class AssistV4Fragment : Fragment() {
    lateinit var onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResult.invoke(requestCode, resultCode, data)
    }
}