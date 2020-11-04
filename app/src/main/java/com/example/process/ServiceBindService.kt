package com.example.process

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.talk.MessageUtils
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 *  author: wangming
 *  email:  cy_wangming@163.com
 *  date:   2020/10/28 18:09
 */
class ServiceBindService : Service() {
    val TAG = "ServiceBindService"

    override fun onCreate() {
        super.onCreate()
        val bindService = MessageUtils.INSTANCE.bindService(this)
        Log.d(TAG, "$TAG 绑定----$bindService")

        startLoopGetMsg()
    }

    @SuppressLint("CheckResult")
    private fun startLoopGetMsg() {
        Observable.interval(0, 5, TimeUnit.SECONDS)
            .compose(SchedulerFormer())
            .subscribe {
                Log.d(TAG, "msg is :${MessageUtils.INSTANCE.getMessage()}")
            }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}