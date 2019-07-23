package com.creepersan.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.creepersan.root_manager.RootManager
import com.creepersan.root_manager.command.Command
import com.creepersan.root_manager.command.CommandCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInit.setOnClickListener {
            RootManager.getInstance().checkHasRootPermission(object : CommandCallback{
                override fun onResponse(response: String) {
                    log("response -> $response")
                }

                override fun onError(e: Throwable) {
                    log("e")
                }
            })
        }
        btnAddOneCommand.setOnClickListener {
            RootManager.getInstance().sendCommand(Command("su | adb devices", object : CommandCallback{
                override fun onResponse(response: String) {
                    log("Response => $response")
                }

                override fun onError(e: Throwable) {
                    log("onError(Send One)")
                }
            }))
        }
        btnAddTenCommand.setOnClickListener {
            for (i in 0..9){
                RootManager.getInstance().sendCommand(Command("echo $i", object : CommandCallback{
                    override fun onResponse(response: String) {
                        log("Response => $response")
                    }

                    override fun onError(e: Throwable) {
                        log("onError(Send One)")
                    }
                }))
            }
        }
        btnAction.setOnClickListener {
            RootManager.getInstance().action()
        }
    }

    private fun log(content:String){
        Log.e("TAG", content)
    }
}

