package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat

class DelBind : Command {
    override val aliases = arrayOf("delbind", "del")
    override val syntax = "[module]"

    override fun onCommand(args: List<String>){

        var mod = Manager.getModuleByName(args[0])
        if(mod == null) Chat.errorMessage("The name of the module is wrong :(")
        else {
            mod.key = -2
            Chat.clientMessage("Bind removed for" + mod.name)
        }
    }
}