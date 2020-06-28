package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat

class Adjust : Command {
    override val aliases = arrayOf("adjust", "a")
    override val syntax = "[module] [name of setting] [new value]"

    override fun onCommand(args: List<String>) {
        if (args.size < 3) {
            Chat.errorMessage("Syntax is incorrect")
            return
        }
        for (mod in Manager.modules) {
            if (Chat.compare(mod.name,args[0])) {
                for (set in mod.settings) {
                    if (Chat.compare(set.name,args[1])) {
                        try {
                            if (set.value is Boolean) {
                                set.value = args[2].toBoolean()
                                Chat.clientMessage(args[1] + " was set to (boolean) " + args[2])
                                return
                            }
                            if (set.value is Int) {
                                set.value = args[2].toInt()
                                Chat.clientMessage(args[1] + " was set to (int) " + args[2])
                                return
                            }
                            if (set.value is Double) {
                                set.value = args[2].toDouble()
                                Chat.clientMessage(args[1] + " was set to (double) " + args[2])
                                return
                            }
                            Chat.errorMessage("Setting value is that of an unsupported type")
                        } catch (err: Exception){
                            Chat.errorMessage("Value couldn't be parsed... $err")
                        }
                        return
                    }
                }
                Chat.errorMessage("No setting with name " + args[1] + " was found")
                return
            }
        }
        Chat.errorMessage("No module with name " + args[0] + " was found")
    }
}