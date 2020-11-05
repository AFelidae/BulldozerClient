package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe

class EntityControl: Module("EntityControl", arrayOf(SettingBoolean("YawLock", true)), true) {
    //LamaEntity.java

    @Subscribe
    fun onTick(event: Tick){
        if((settings[0] as SettingBoolean).value) mc.player!!.vehicle!!.yaw = mc.player!!.yaw
    }
}