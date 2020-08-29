package bulldozer.command

import bulldozer.Command
import bulldozer.utils.Chat
import kotlin.math.floor
import net.minecraft.text.LiteralText

class Disconnect : Command {
    override val aliases = arrayOf("disconnect", "dc")
    override val syntax = ""

    override fun onCommand(args: List<String>){
        mc.player!!.networkHandler.connection.disconnect(LiteralText(":D"));
    }
}