package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.Manager.getModule
import bulldozer.module.Gui
import bulldozer.utils.Chat

class Bind : Command {

    override val aliases = arrayOf("bind", "b")
    override val syntax = "[module] | [module] [bind]"

    override fun onCommand(args: List<String>) {
        if(args.isNotEmpty()) {
            val bg = getModule(Gui::class.java) as Gui?
            bg!!.binding = true
            bg.selected = Manager.getModuleByName(args[0])!!
            Chat.clientMessage("Press a key to bind " + args[0])
        }
    }
}