package bulldozer.gui

class SettingString {
    val name: String
    var value: String

    constructor(text: String, default: String){
        name = text
        value = default
    }
}