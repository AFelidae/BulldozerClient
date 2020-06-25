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
            if (mod.name.toLowerCase().replace(" ", "") == args[0].toLowerCase()) {
                for (set in mod.settings) {
                    if (set.name.toLowerCase().replace(" ", "") == args[1].toLowerCase()) {
                        try {
                            if (set.value is Boolean) set.value = args[2].toBoolean()
                            if (set.value is Int) set.value = args[2].toInt()
                            if (set.value is Double) set.value = args[2].toDouble()
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