package com.example.process

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.talk.MessageUtils

class ClientActivity02 : AppCompatActivity() {
    val TAG = "ProcessActivity02"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_02)
        val bindService = bindService()
        Log.d(TAG, "$TAG 绑定----$bindService")

        findViewById<View>(R.id.set_msg).setOnClickListener {
            MessageUtils.INSTANCE.setMessage("ProcessActivity02020202")
        }

        findViewById<View>(R.id.get_msg).setOnClickListener {
            Log.d(TAG, "$TAG 获取到的msg----${MessageUtils.INSTANCE.getMessage()}")
        }
    }

    private fun bindService(): Boolean {
        return MessageUtils.INSTANCE.bindService(this)
    }
}
