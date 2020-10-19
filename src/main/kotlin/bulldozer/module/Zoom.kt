package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingFloat

class Zoom: Module("Zoom", arrayOf(SettingFloat("Level", 2.0f, 1.0f, 64.0f)), true) {
    private var sens: Double = 0.0

    override fun onEnable(){
        sens = mc.options.mouseSensitivity
        mc.options.mouseSensitivity = mc.options.mouseSensitivity / (settings[0] as SettingFloat).value
    }

    override fun onDisable(){
        mc.options.mouseSensitivity = sens
    }
    //GameRenderer.java
}
