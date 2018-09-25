package com.example.library

import android.app.Activity
import android.app.FragmentManager
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity

private typealias CallBack = (ActivityBackWrapper) -> Unit
private typealias V4FragmentManager = android.support.v4.app.FragmentManager
private typealias AppFragmentManager = android.app.FragmentManager
private typealias AppFragment = android.app.Fragment
private typealias V4Fragment = android.support.v4.app.Fragment

private const val TAG_FRAGMENT = "com.example.library.AssistFragment"

@RequiresApi(Build.VERSION_CODES.N)
fun Activity.startActivityWithCallBack(intent: Intent, requestCode: Int, callBack: CallBack?) {
    startActivityWithCallBack(fragmentManager, intent, requestCode, callBack)
}

@RequiresApi(Build.VERSION_CODES.N)
fun AppFragment.startActivityWithCallBack(intent: Intent, requestCode: Int, callBack: CallBack?) {
    startActivityWithCallBack(activity.fragmentManager, intent, requestCode, callBack)
}


fun FragmentActivity.startActivityWithCallBack(intent: Intent, requestCode: Int, callBack: CallBack?) {
    startV4ActivityWithCallBack(supportFragmentManager, intent, requestCode, callBack)
}

fun V4Fragment.startActivityWithCallBack(intent: Intent, requestCode: Int, callBack: CallBack?) {
    startV4ActivityWithCallBack(childFragmentManager, intent, requestCode, callBack)
}

private fun startV4ActivityWithCallBack(fragmentManager: V4FragmentManager, intent: Intent, requestCode: Int, callBack: CallBack?) {
    var fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT) as? AssistV4Fragment
    if (fragment == null) {
        fragment = AssistV4Fragment()
        fragmentManager
                .beginTransaction()
                .add(fragment, TAG_FRAGMENT)
                .commitNowAllowingStateLoss()//此处采用commitNowAllowingStateLoss
    }
    fragment.onActivityResult = { requestCode, resultCode, data ->
        callBack?.invoke(ActivityBackWrapper(data, requestCode, resultCode))
    }
    fragment.startActivityForResult(intent, requestCode)
}

private val mainHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
private val pendingRequestManagerFragments = mutableMapOf<FragmentManager, AssistFragment>()

@RequiresApi(Build.VERSION_CODES.N)
private fun startActivityWithCallBack(fragmentManager: AppFragmentManager, intent: Intent, requestCode: Int, callBack: CallBack?) {
    var fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT) as? AssistFragment
    if (fragment == null) {
        fragment = pendingRequestManagerFragments[fragmentManager]
        if (fragment == null) {
            fragment = AssistFragment()
            pendingRequestManagerFragments[fragmentManager] = fragment
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG_FRAGMENT)
                    .commitAllowingStateLoss()//此处采用commitAllowingStateLoss
        }
    }
    mainHandler.post {
        pendingRequestManagerFragments.remove(fragmentManager)
        fragment.onActivityResult = { requestCode, resultCode, data ->
            callBack?.invoke(ActivityBackWrapper(data, requestCode, resultCode))
        }
        fragment.startActivityForResult(intent, requestCode)
    }
}