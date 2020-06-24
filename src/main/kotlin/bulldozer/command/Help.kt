package bulldozer.command

import bulldozer.Command

class Help: Command {
    override var syntax = ""
    override var aliases = arrayOf("help")
}