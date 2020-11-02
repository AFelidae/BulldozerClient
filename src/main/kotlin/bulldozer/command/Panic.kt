package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.module.Mute

class Panic: Command {
    override val aliases = arrayOf("panic", "shutdown")
    override val syntax = ""

    override fun onCommand(args: List<String>) {
        for(mod in Manager.modules){
            if(mod.toggled) mod.toggle()
        }
    }
}