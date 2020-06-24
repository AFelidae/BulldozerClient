package bulldozer

interface Command {
    var syntax: String
    var aliases: Array<String>

    fun getName(): String {
        return aliases[0]
    }

    fun onCommand(){}
}