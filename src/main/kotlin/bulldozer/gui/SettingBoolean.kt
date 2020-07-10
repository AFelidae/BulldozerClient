package bulldozer.gui

class SettingBoolean: SettingGenericBase{
    override val name: String
    var value: Boolean

    constructor(text: String, default: Boolean){
        name = text
        value = default
    }
}