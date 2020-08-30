package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingFloat

class Zoom: Module("Zoom", arrayOf(SettingFloat("zoom", 2.0f, 1.0f, 64.0f))) {
    open var level: Float = 1.0f
    open var sens: Double = 0.0

    override fun onEnable(){
        sens = mc.options.mouseSensitivity
        level = (settings[0] as SettingFloat).value
        mc.options.mouseSensitivity = mc.options.mouseSensitivity / level
    }

    override fun onDisable(){
        level = 1.0f;
        mc.options.mouseSensitivity = sens
    }
}
