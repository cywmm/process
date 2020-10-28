package com.example.talk

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process.myPid
import android.util.Log

/**
 *  author: wangming
 *  email:  cy_wangming@163.com
 *  date:   2020/10/28 10:54
 */
class ProcessService : Service() {
    private val TAG = ProcessService::class.java.simpleName
    private var message: String? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private val binder: IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {

        override fun getPid(): Int {
            return myPid()
        }

        override fun setMsg(aString: String) {
            Log.d(TAG, "ProcessService set message is $aString")
            message = aString
        }

        override fun getMsg(): String? {
            Log.d(TAG, "ProcessService get message is $message")
            return message
        }
    }
}