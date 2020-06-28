package bulldozer.module

import bulldozer.Manager
import bulldozer.Module
import bulldozer.gui.Setting

class Zoom: Module("zoom", "Fly around the map", arrayOf(Setting("zoom", 2.0f, 1.0f, 64.0f))) {
    open var level: Float = 1.0f


    override fun onEnable(){
        level = settings[0].value as Float
    }

    override fun onDisable(){
        level = 1.0f;
    }
}
