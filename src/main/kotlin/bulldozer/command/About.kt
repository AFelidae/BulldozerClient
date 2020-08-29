package bulldozer.command

import bulldozer.Command
import bulldozer.utils.Chat
import kotlin.math.floor

class About : Command {
    override val aliases = arrayOf("about")
    override val syntax = ""

    override fun onCommand(args: List<String>){
        if (mc.isInSingleplayer){
            Chat.errorMessage("Can only be used in multiplayer ;c");
            return;
        }
        Chat.clientMessage("Name: "+mc.currentServerEntry!!.name);
        Chat.clientMessage("Version: "+mc.currentServerEntry!!.version);
        Chat.clientMessage("IP: "+mc.currentServerEntry!!.address);
        Chat.clientMessage("Players: "+mc.currentServerEntry!!.playerCountLabel);
    }
}