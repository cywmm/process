package com.example.process

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.talk.MessageUtils

class ClientActivity01 : AppCompatActivity() {
    val TAG = "ProcessActivity01"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_01)
        val bindService = bindService()
        MessageUtils.INSTANCE.setMessage("ProcessActivity01010101")
        Log.d(TAG, "$TAG 绑定----$bindService")
        Log.d(TAG, "$TAG 获取到的msg----${MessageUtils.INSTANCE.getMessage()}")


        findViewById<View>(R.id.start_01).setOnClickListener {
            startService(Intent(this, ServiceBindService::class.java))
        }

        findViewById<View>(R.id.start_02).setOnClickListener {
            startActivity(Intent(this, ClientActivity02::class.java))
        }

        findViewById<View>(R.id.set_msg).setOnClickListener {
            MessageUtils.INSTANCE.setMessage("ProcessActivity01010101")
        }

        findViewById<View>(R.id.get_msg).setOnClickListener {
            Log.d(TAG, "$TAG 获取到的msg----${MessageUtils.INSTANCE.getMessage()}")
        }
    }

    private fun bindService(): Boolean {
        return MessageUtils.INSTANCE.bindService(this)
    }
}
