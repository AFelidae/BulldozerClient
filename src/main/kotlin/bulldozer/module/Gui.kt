package bulldozer.module

import bulldozer.Module

class Gui:  Module("Gui", emptyArray<Any>()) {
    @kotlin.jvm.JvmField
    var binding = false
    lateinit var selected: Module

    override fun onDisable(){
        mc.openScreen(null)
    }
}