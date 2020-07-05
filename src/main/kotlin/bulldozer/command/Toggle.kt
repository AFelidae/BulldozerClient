package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat
import bulldozer.Module

class Toggle : Command {
    override val aliases = arrayOf("toggle", "t")
    override val syntax = "[module]"

    override fun onCommand(args: List<String>) {
        val mod: Module? = Manager.getModuleByName(args[0])
        if(mod == null) Chat.errorMessage("The name of the module is wrong :(")
        else {
            mod.toggle()
            Chat.clientMessage(mod.name + " has been toggled to " + if(mod.toggled) "On" else "Off")
            return
        }
    }
}