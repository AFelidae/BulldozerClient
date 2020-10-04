package bulldozer.command

import bulldozer.Command
import bulldozer.Manager
import bulldozer.Module
import bulldozer.module.Mute
import bulldozer.utils.Chat

class Say: Command {
    override val aliases = arrayOf("say", "s")
    override val syntax = "[whatever you want to say]"

    override fun onCommand(args: List<String>) {
        val mute = Manager.getModule(Mute::class.java) as Mute?
        mute!!.temporaryException = true
        mc.player!!.sendChatMessage(args.joinToString(separator = " "))
    }
}