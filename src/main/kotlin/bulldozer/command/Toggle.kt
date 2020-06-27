package bulldozer.command

import bulldozer.Command;
import bulldozer.Manager;
import bulldozer.utils.Chat

class Toggle : Command {
    override val aliases = arrayOf("toggle", "t")
    override val syntax = "[module]"

    override fun onCommand(args: List<String>) {
        if(args.isEmpty()){
            Chat.errorMessage("No module specified")
            return
        }
        for(mod in Manager.modules){
            if (mod.name.toLowerCase().trim() == args[0].toLowerCase().trim()){
                mod.toggle()
                Chat.clientMessage(mod.name + "has been toggled to " + if(mod.toggled) "On" else "Off")
                return
            }
        }
        Chat.errorMessage("No module with name " + args[0] + " was found")
    }
}