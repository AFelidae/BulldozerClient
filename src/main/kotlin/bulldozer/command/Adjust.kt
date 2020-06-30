package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat

class Adjust : Command {
    override val aliases = arrayOf("adjust", "a")
    override val syntax = "[module] | [module] [name of setting] [new value]"

    override fun onCommand(args: List<String>) {
            val mod = Manager.getModuleByName(args[0])
            if(mod === null) Chat.errorMessage("The name of the module is wrong :(")
            else {
                when (args.size) {
                    1 -> {
                        for(set in mod.settings){
                            Chat.clientMessage(set.name + " : " + set.value.toString())
                        }
                    }
                    3 -> {
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
                                    if (set.value is Float){
                                        set.value = args[2].toFloat()
                                        Chat.clientMessage(args[1] + " was set to (float) " + args[2])
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
                    else -> {
                        Chat.errorMessage("The syntax of the command is malformed")
                    }
                }
            }
    }
}