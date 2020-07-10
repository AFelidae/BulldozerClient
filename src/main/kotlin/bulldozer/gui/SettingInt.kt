package bulldozer.gui

class SettingInt: SettingGenericBase {
    override val name: String
    var value: Int
    val minimum: Int
    val maximum: Int

    constructor(text: String, default: Int, min: Int, max:Int){
        name = text
        value = default
        minimum = min
        maximum = max
    }
}