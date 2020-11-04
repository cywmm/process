package com.example.talk

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

/**
 *  author: wangming
 *  email:  cy_wangming@163.com
 *  date:   2020/11/3 15:04
 */
enum class MessageUtils {
    INSTANCE;

    val TAG = "MessageUtils"

    private var iMyAidlInterface: IMyAidlInterface? = null

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected----$name")
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "Service has unexpectedly disconnected----$name")
            iMyAidlInterface = null
        }
    }

    fun bindService(activity: Context): Boolean {
        val intent = Intent()
        intent.component = ComponentName(activity, SeverService::class.java)
        return activity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    fun getMessage(): String? {
        return iMyAidlInterface?.msg
    }

    fun setMessage(message: String) {
        iMyAidlInterface?.msg = message
    }
}