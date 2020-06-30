package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat
import net.minecraft.client.util.InputUtil

class Bind : Command {

    override val aliases = arrayOf("bind", "b")
    override val syntax = "[module] | [module] [bind]"

    override fun onCommand(args: List<String>) {
        if(args.isNotEmpty()) {
            Manager.binding = true
            Manager.bindname = args[0]
            Chat.clientMessage("Press a key to bind " + args[0])
        }
    }
}