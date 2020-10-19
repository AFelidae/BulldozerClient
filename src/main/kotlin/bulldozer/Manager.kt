package bulldozer

import bulldozer.command.*
import bulldozer.module.*
import bulldozer.utils.Chat
import com.google.common.eventbus.EventBus
import java.lang.Exception

object Manager {
    @kotlin.jvm.JvmField
    open var eventSystem: EventBus = EventBus()
    @kotlin.jvm.JvmField
    var binding = false
    @kotlin.jvm.JvmField
    open var bindname = ""

    var modules = arrayOf(
        Ambience(),
        Brightness(), //->Bright
        CameraClip(),
        Chams(),
        ChestEsp(),
        EntityControl(),
        EntitySpeed(),
        EntitySpider(),
        EntityStep(),
        Esp(),
        FallDamage(),
        Flight3d(),
        FlightStatic(),
        Freecam(),
        Gui(),
        Headdesk(),
        Highlight(),
        KillAura(),
        Leap(),
        Momentum(),
        Mute(),
        Parkour(),
        Respawn(),
        Sneak(),
        Speed(),
        Spider(),
        Step(),
        Strafe(),
        Timer(),
        Totem(),
        Tracers(),
        UnfocusedCPU(),
        Zoom())

    var commands = arrayOf(
        Adjust(),
        Bind(),
        Bottom(),
        Clear(),
        DelBind(),
        Disconnect(),
        Fancy(),
        Help(),
        Say(),
        Server(),
        Top(),
        Toggle())

    @JvmStatic
    fun callCommand(command: String) {
        val target: String
        val parameters: List<String>

        if (command.indexOf(" ") > -1) {
            target = command.substring(0, command.indexOf(" ")).toLowerCase()
            parameters = command.substring(command.indexOf(" ")+1).trim().split(" ")
        } else {
            target = command.toLowerCase()
            parameters = emptyList()
        }

        for (cmd in commands) {
            for (alias in cmd.aliases) {
                if (Chat.compare(target, alias)) try{ cmd.onCommand(parameters) } catch(Err: Exception){
                    Chat.errorMessage(cmd.syntax)
                }
            }
        }
    }

    @JvmStatic
    fun fromKey(keycode:Int): Boolean{
        var found = false
        for(mod in modules){
            if(mod.key == keycode){
                found = true
            }
        }
        return found
    }

    @JvmStatic
    fun getModule(targetClass: Class<out Module?>): Module? {
        for (module in modules) {
            if (module.javaClass == targetClass) {
                return module
            }
        }
        return null
    }

    @JvmStatic
    fun getModuleByName(name:String): Module? {
        for(mod in modules){
            if(Chat.compare(mod.name, name)){
                return mod;
            }
        }
        return null
    }

    @JvmStatic
    fun getCommandByName(name:String): Command? {
        for(com in commands){
            if(Chat.compare(com.aliases[0], name)) {
                return com;
            }
        }
        return null
    }
}