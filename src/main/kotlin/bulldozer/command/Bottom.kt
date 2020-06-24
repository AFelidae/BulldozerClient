package bulldozer.command

import bulldozer.Command
import bulldozer.utils.Chat
import kotlin.math.floor

class Bottom : Command {
    override val aliases = arrayOf("bottom")
    override val syntax = "[offset]"

    override fun onCommand(args: List<String>){
        var hitsolid = false
        var offset = if(args.isEmpty() || args[0].isEmpty()) {
            0.0
        } else {
            args[0].toDouble()
        }

        for (i in 0..255) {
            if (hitsolid) {
                if (mc.world!!.getBlockState(mc.player!!.blockPos.down(i)).isAir) {
                    mc.player!!.updatePosition(
                        mc.player!!.x,
                        floor(mc.player!!.y) - i - 1.0 + offset,
                        mc.player!!.z
                    )
                    return
                }
            } else {
                if (!mc.world!!.getBlockState(mc.player!!.blockPos.down(i)).isAir) hitsolid = true
            }
        }
        Chat.errorMessage("Could not find floor below you")
    }
}