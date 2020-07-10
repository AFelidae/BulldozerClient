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
        if(mod == null){
            Chat.clientMessage("Could not find module named " + args[0])
            return
        }
        if(args.size == 1){
            for(setting in mod.settings)
                when(setting){
                    is SettingBoolean ->
                        Chat.clientMessage("Value of " + setting.name + " is (boolean) " + setting.value.toString())
                    is SettingDouble ->
                        Chat.clientMessage("Value of " + setting.name + " is (double) " + setting.value.toString())
                    is SettingFloat ->
                        Chat.clientMessage("Value of " + setting.name + " is (float) " + setting.value.toString())
                    is SettingInt ->
                        Chat.clientMessage("Value of " + setting.name + " is (int) " + setting.value.toString())
                    is SettingMode -> {
                        var s: String = ""
                        for (m in setting.modes){
                            s += " | $m"
                        }
                        Chat.clientMessage("Value of " + setting.name + " is (mode ("+s+")) " + setting.modes[setting.value])
                    }
                    is SettingString ->
                        Chat.clientMessage("Value of " + setting.name + " is (boolean) " + setting.value)
            }
        }
        else if(args.size == 3) {
            try {
                val setting = mod.getSettingByName(args[1])
                if(setting == null){
                    Chat.clientMessage("Could not find setting named " + args[1])
                    return
                }
                when(setting){
                    is SettingBoolean -> {
                        setting.value = args[2].toBoolean()
                        Chat.clientMessage("Setting value " + setting.name + "changd to (boolean) " + setting.value.toString())
                    }
                    is SettingDouble -> {
                        setting.value = args[2].toDouble()
                        Chat.clientMessage("Setting value " + setting.name + "changd to (double) " + setting.value.toString())
                    }
                    is SettingFloat -> {
                        setting.value = args[2].toFloat()
                        Chat.clientMessage("Setting value " + setting.name + "changd to (float) " + setting.value.toString())
                    }
                    is SettingInt -> {
                        setting.value = args[2].toInt()
                        Chat.clientMessage("Setting value " + setting.name + "changd to (int) " + setting.value.toString())
                    }
                    is SettingMode -> {
                        for(i in 1..setting.modes.size){
                            if(Chat.compare(setting.modes[i-1],args[2])) setting.value = i-1
                        }
                        Chat.clientMessage("Setting value " + setting.name + "changd to (mode) " + setting.modes[setting.value])
                    }
                    is SettingString -> {
                        setting.value = args[2]
                        Chat.clientMessage("Setting value " + setting.name + "changd to (string) " + setting.value)
                    }
                }
            } catch (err: Exception) {
                Chat.clientMessage("Couldn't convert text to the setting type")
            }
        }
        else Chat.clientMessage("Malformed command syntax")
    }
}