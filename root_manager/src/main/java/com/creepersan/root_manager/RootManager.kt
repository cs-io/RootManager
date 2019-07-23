package com.creepersan.root_manager

import com.creepersan.root_manager.command.Command
import com.creepersan.root_manager.command.CommandCallback
import com.creepersan.root_manager.terminal.Terminal

class RootManager private constructor(){

    companion object{
        @Volatile private var mInstance : RootManager? = null

        fun getInstance(): RootManager {
            if (mInstance == null){
                synchronized(this){
                    if (mInstance == null){
                        mInstance = RootManager()
                    }
                }
            }
            return mInstance!!
        }
    }

    private val mTerminal = Terminal(Runtime.getRuntime())

    fun checkHasRootPermission(callback:CommandCallback){
        mTerminal.execute(Command("su", callback))
    }

    fun sendCommand(command: Command){
        mTerminal.execute(command)
    }

    fun action(){
        mTerminal.action()
    }

}