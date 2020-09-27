package bulldozer.module

import bulldozer.Module

class Gui:  Module("Gui", emptyArray<Any>()) {
    @kotlin.jvm.JvmField
    var selected: Module? = null

    @kotlin.jvm.JvmField
    var binding = false

    override fun onDisable(){
        mc.openScreen(null)
    }
}