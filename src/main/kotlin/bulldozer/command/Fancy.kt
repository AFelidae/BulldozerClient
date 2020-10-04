package bulldozer.command

import bulldozer.Command


class Fancy: Command {
    override val aliases = arrayOf("fancy")
    override val syntax = "[whatever you want to say in fancy font]"

    override fun onCommand(args: List<String>) {
        val message = args.joinToString(separator = " ").toLowerCase()

        var output: String? = ""

        for (l in message.toCharArray()) {
            if (l.toInt() < 0x21 || l.toInt() > 0x80) output += l else output += String(Character.toChars(l.toInt() + 0xfee0))
        }

        mc.player!!.sendChatMessage(output)
    }
}