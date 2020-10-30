package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.utils.Chat
import bulldozer.utils.TNTUtils

class WeightTest: Command {
    override val syntax = ""
    override val aliases = arrayOf("WeightTest", "Weight", "Weigh", "WT")

    override fun onCommand(args: List<String>) {
        var weight = TNTUtils.getWeight(mc.player!!.blockPos.down(1))
        Chat.clientMessage("Weight: $weight")
    }
}