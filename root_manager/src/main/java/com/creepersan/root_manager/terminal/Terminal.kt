package com.creepersan.root_manager.terminal

import android.util.Log
import com.creepersan.root_manager.command.Command
import com.creepersan.root_manager.command.CommandQueue
import java.io.*
import java.lang.StringBuilder

class Terminal(val mRuntime: Runtime){

    fun execute(command: Command){
        val commandStr = command.command
        val callback = command.callback
        try {
            //Process中封装了返回的结果和执行错误的结果
            val mProcess = mRuntime.exec(commandStr)
            val mReader = BufferedReader(InputStreamReader(mProcess.inputStream))
            val mRespBuff = StringBuffer()
            val buff = CharArray(1024)
            var ch = 0
            do {
                ch = mReader.read(buff)
                if (ch != -1){
                    mRespBuff.append(buff, 0, ch)
                }
            }while (ch != -1)
            mReader.close()
            callback?.onResponse(mRespBuff.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            callback?.onError(e)
        }

    }


    fun action111(){
        try {
            //Process中封装了返回的结果和执行错误的结果
            val mProcess = mRuntime.exec("su")
            val outStream = DataOutputStream(mProcess.outputStream)
            outStream.writeBytes("ls")
            outStream.writeBytes("cd ~")
            outStream.writeBytes("echo --------------------")
            val mReader = BufferedReader(InputStreamReader(mProcess.inputStream))
            val mRespBuff = StringBuffer()
            val buff = CharArray(1024)
            var ch = 0
            do {
                ch = mReader.read(buff)
                if (ch != -1){
                    mRespBuff.append(buff, 0, ch)
                }
            }while (ch != -1)
            mReader.close()
            Log.e("TAG", mRespBuff.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("TAG", "Error")
        }
    }

    fun action(){
        try {
            val stringBuilder = StringBuilder()
            //Process中封装了返回的结果和执行错误的结果
            val mProcess = ProcessBuilder("/system/bin/sh").apply {
                directory(File("/"))
            }.start()
            val outStream = DataOutputStream(mProcess.outputStream)
            val inStream = mProcess.inputStream
            outStream.writeBytes("su -c 'cd /data/data;ls'\n")
            outStream.writeBytes("exit\n")
            outStream.flush()
            mProcess.waitFor()

            val cacheArray = ByteArray(999999)
            var length = inStream.read(cacheArray)
            while (length >= 0){
                stringBuilder.append(String(cacheArray, 0, length))
                length = inStream.read(cacheArray)
            }

            Log.e("TAG", stringBuilder.toString())

        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("TAG", "Error")
        }
    }

}