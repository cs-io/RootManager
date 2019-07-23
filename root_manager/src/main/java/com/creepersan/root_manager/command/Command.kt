package com.creepersan.root_manager.command

data class Command(
    var command:String,
    var callback: CommandCallback? = null
){
    var isSend = false
        internal set
    var isResponse = false
        internal set

    init {
        if (!command.endsWith("\n")){
            command = "$command\n"
        }
    }

}