package bulldozer.gui


class Setting {
    lateinit var name: String
    lateinit var value: Any
    lateinit var minimum: Any
    lateinit var maximum: Any
    lateinit var modes: Array<String>

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

    constructor(text: String, default: Float, min: Float, max: Float){
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

    constructor(text: String, options: Array<String>){
        name = text
        modes = options
        value = 0
    }
}