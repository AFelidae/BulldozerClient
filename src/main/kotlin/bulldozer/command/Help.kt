package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat

class Help: Command {
    override val syntax = ""
    override val aliases = arrayOf("help")

    override fun onCommand(args: List<String>) {
        if(args.isNotEmpty()){ //Im really curious if whoever added the not version of isEmpty was on a quota
            val mod = Manager.getModuleByName(args[1])
            if(mod == null) Chat.errorMessage("The name of the module is wrong :(")
        } else {
            for(cmd in Manager.commands){
                Chat.clientMessage("-" + cmd.getName() + " " + cmd.syntax)
            }
        }
    }
}