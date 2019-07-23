package com.creepersan.root_manager.command

import java.util.*

class CommandQueue{

    private val mQueue = LinkedList<Command>()

    fun getSize():Int{
        return mQueue.size
    }

    @Synchronized
    fun sendCommand(command: Command){
        mQueue.addLast(command)
    }

    @Synchronized
    fun getCommand(): Command?{
        if (mQueue.isNotEmpty()){
            return mQueue.removeFirst()
        }
        return null
    }

    @Synchronized
    fun hasCommand():Boolean{
        return mQueue.isNotEmpty()
    }

    @Synchronized
    fun clear(){
        mQueue.clear()
    }

    @Synchronized
    fun cancel(command: Command){
        mQueue.remove(command)
    }

}