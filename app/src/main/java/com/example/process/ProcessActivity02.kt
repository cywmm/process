package com.example.process

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.example.talk.IMyAidlInterface
import com.example.talk.ProcessService

class ProcessActivity02 : AppCompatActivity() {
    val TAG = "ProcessActivity02"
    var iMyAidlInterface: IMyAidlInterface? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_02)
        bindService()
        val bindService = bindService()
        Log.d(TAG, "$TAG 绑定----$bindService")

        findViewById<View>(R.id.set_msg).setOnClickListener {
            iMyAidlInterface?.msg = "ProcessActivity020202"
        }

        findViewById<View>(R.id.get_msg).setOnClickListener {
            Log.d(TAG, "$TAG 获取到的msg----${iMyAidlInterface?.msg}")
        }
    }

    private fun bindService(): Boolean {
        val intent = Intent()
        intent.component = ComponentName(this
            , ProcessService::class.java)
        return bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }
}
