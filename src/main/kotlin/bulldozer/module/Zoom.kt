package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.Setting
import bulldozer.utils.Options
import com.google.common.eventbus.Subscribe

class Zoom: Module("zoom", "Fly around the map", arrayOf(Setting("zoom", 2.0, 1.0, 64.0))) {

    @Subscribe
    fun onTick(event: Render3D){
        if(toggled) Options.zoom = settings[0].value as Double
        else Options.zoom = 1.0
    }
}
