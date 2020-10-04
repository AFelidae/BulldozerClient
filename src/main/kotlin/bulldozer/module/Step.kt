package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingFloat
import com.google.common.eventbus.Subscribe

class Step: Module("Step", arrayOf<Any>(SettingFloat("Height",1f, 0.5f, 10f))) {

    @Subscribe
    fun onTick(event: Tick) {
        if(toggled) mc.player!!.stepHeight = (settings[0] as SettingFloat).value;
        else mc.player!!.stepHeight = 0.5f;
    }
}