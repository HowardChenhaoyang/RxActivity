package com.example.library

import android.content.Intent
import android.support.v4.app.FragmentActivity

private val fragmentTag = "com.example.library.AssistFragment"
fun FragmentActivity.startActivityWithCallBack(intent: Intent, requestCode: Int, callBack: (ActivityBackWrapper) -> Unit) {
    var fragment = supportFragmentManager.findFragmentByTag(fragmentTag) as? AssistFragment
    if (fragment == null) {
        fragment = AssistFragment()
        supportFragmentManager
                .beginTransaction()
                .add(fragment, fragmentTag)
                .commitNowAllowingStateLoss()//此处采用commitNowAllowingStateLoss
    }
    fragment!!.onActivityResult = { requestCode, resultCode, data ->
        callBack(ActivityBackWrapper(data, requestCode, resultCode))
    }
    fragment.startActivityForResult(intent, requestCode)
}