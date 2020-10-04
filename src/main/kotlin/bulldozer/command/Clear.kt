package bulldozer.command

import bulldozer.Command

class Clear: Command {
    override val aliases = arrayOf("clear")
    override val syntax = ""

    override fun onCommand(args: List<String>) {
        mc.inGameHud.chatHud.clear(false);
    }
}