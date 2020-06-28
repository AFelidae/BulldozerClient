package bulldozer

import bulldozer.command.*
import bulldozer.module.Flight3d
import bulldozer.module.FlightStatic
import bulldozer.module.Zoom
import com.google.common.eventbus.EventBus


object Manager {
    @kotlin.jvm.JvmField
    open var eventSystem: EventBus = EventBus()
    var modules = arrayOf(
        Flight3d(),
        FlightStatic(),
        Zoom())



    var commands = arrayOf(
        Adjust(),
        Bottom(),
        Help(),
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
                if (target == alias) cmd.onCommand(parameters)
            }
        }
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
}