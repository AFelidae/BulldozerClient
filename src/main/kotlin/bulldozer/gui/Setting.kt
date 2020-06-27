package bulldozer.gui


class Setting {
    lateinit var name: String
    lateinit var value: Any
    lateinit var minimum: Any
    lateinit var maximum: Any

    constructor(text: String, default: Boolean){
        name = text
        value = default
    }

    constructor(text: String, default: Int, min: Int, max: Int){
        name = text
        value = default
        minimum = min
        maximum = max
    }

    constructor(text: String, default: Double, min: Double, max: Double){
        name = text
        value = default
        minimum = min
        maximum = max

    }
}