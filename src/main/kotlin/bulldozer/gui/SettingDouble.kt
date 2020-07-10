package bulldozer.gui

class SettingDouble {
    val name: String
    var value: Double
    val minimum: Double
    val maximum: Double

    constructor(text: String, default: Double, min: Double, max:Double){
        name = text
        value = default
        minimum = min
        maximum = max
    }
}