package com.creepersan.root_manager.command

interface CommandCallback{

    fun onResponse(response:String)

    fun onError(e:Throwable)

}