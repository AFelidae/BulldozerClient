package bulldozer.module

import bulldozer.Module
import bulldozer.gui.ClickGui
import net.minecraft.client.MinecraftClient

class Gui:  Module("BulldozerGui", emptyArray()) {
    @kotlin.jvm.JvmField
    var binding = false
    lateinit var selected: Module

    override fun onDisable(){
        mc.openScreen(null)
    }
}