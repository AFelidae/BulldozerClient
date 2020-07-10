package bulldozer.gui

class SettingString: SettingGenericBase {
    override val name: String
    var value: String

    constructor(text: String, default: String){
        name = text
        value = default
    }
}