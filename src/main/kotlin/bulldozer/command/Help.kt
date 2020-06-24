package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat

class Help: Command {
    override val syntax = ""
    override val aliases = arrayOf("help")

    override fun onCommand(args: List<String>) {
        for(cmd in Manager.commands){
            Chat.clientMessage("-" + cmd.getName() + " " + syntax)
            //mc. cmd.getName() + cmd.syntax
        }
    }
}