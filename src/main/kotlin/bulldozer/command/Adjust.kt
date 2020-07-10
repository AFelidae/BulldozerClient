package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.gui.*
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
                            for (set in mod.settings)
                                when (set) {
                                    is SettingBoolean ->
                                        Chat.clientMessage(set.name + " is currently (boolean) " + set.value.toString())
                                    is SettingInt ->
                                        Chat.clientMessage(set.name + " is currently (int) " + set.value.toString())
                                    is SettingMode ->
                                        Chat.clientMessage(set.name + " is currently (mode) " + set.modes[set.value])
                                    is SettingDouble ->
                                        Chat.clientMessage(set.name + " is currently (double) " + set.value.toString())
                                    is SettingFloat ->
                                        Chat.clientMessage(set.name + " is currently (float) " + set.value.toString())
                                    is SettingString ->
                                        Chat.clientMessage(set.name + "is currently (string) " + set.value)
                                }
                    }
                    3 -> {
                        for (set in mod.settings) {
                            if (Chat.compare((set as SettingGenericBase).name,args[1])) {
                                try {
                                    when (set) {
                                        is SettingBoolean -> {
                                            set.value = args[2].toBoolean()
                                            Chat.clientMessage(args[1] + " was set to (boolean) " + args[2])
                                            return
                                        }
                                        is SettingInt -> {
                                            set.value = args[2].toInt()
                                            Chat.clientMessage(args[1] + " was set to (int) " + args[2])
                                            return
                                        }
                                        is SettingMode -> {
                                            for(i in 0..set.modes.size-1){
                                                if(Chat.compare(set.modes[i], args[2])){
                                                    set.value = i
                                                    Chat.clientMessage(args[1] + " was set to (mode) " + args[2])
                                                    return
                                                }
                                            }
                                        }
                                        is SettingDouble -> {
                                            set.value = args[2].toDouble()
                                            Chat.clientMessage(args[1] + " was set to (double) " + args[2])
                                            return
                                        }
                                        is SettingFloat -> {
                                            set.value = args[2].toFloat()
                                            Chat.clientMessage(args[1] + " was set to (float) " + args[2])
                                        }
                                        is SettingString -> {
                                            set.value = args[2]
                                            Chat.clientMessage(args[1] + " was set to (string) " + args[2])
                                        }
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