package bulldozer.gui

class SettingMode {
    val name: String
    var value: Int //Index of mode
    var modes: Array<String>

    constructor(text: String, options: Array<String>){
        name = text
        value = 0
        modes = options
    }
}