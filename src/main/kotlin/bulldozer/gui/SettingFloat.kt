package bulldozer.gui

class SettingFloat {
    val name: String
    var value: Float
    val minimum: Float
    val maximum: Float

    constructor(text: String, default: Float, min: Float, max:Float){
        name = text
        value = default
        minimum = min
        maximum = max
    }
}