package bulldozer.module

import bulldozer.Module
import bulldozer.events.ClientMove
import bulldozer.events.Tick
import bulldozer.gui.SettingDouble
import com.google.common.eventbus.Subscribe

class Leap: Module("Leap", arrayOf(SettingDouble("Horizontal", 2.0, 1.0,5.0), SettingDouble("Vertical",1.0,0.5, 5.0))) {
    @Subscribe
    fun onMove(event: ClientMove){
        if(!toggled) return
        if(event.movement.y > 0 && mc.player!!.isOnGround){
            mc.player!!.velocity = mc.player!!.velocity.multiply((settings[0] as SettingDouble).value,(settings[1] as SettingDouble).value,(settings[0] as SettingDouble).value)
        }
    }
}