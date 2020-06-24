package bulldozer

import bulldozer.command.*;

object Manager {
    var modules = arrayOf(0)

    var commands = arrayOf(
        Bottom(),
        Help(),
        Top())

    @JvmStatic
    fun callCommand(command: String) {
        val target: String
        val parameters: List<String>

        if (command.indexOf(" ") > -1) {
            target = command.substring(0, command.indexOf(" ")).toLowerCase()
            parameters = command.substring(command.indexOf(" ")).split(" ")
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
}