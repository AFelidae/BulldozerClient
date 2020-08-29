package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Step: Module("Step", emptyArray<Any>()) {

    @Subscribe
    fun onTick(event: Tick) {
        if(toggled && mc.player!!.horizontalCollision) mc.player!!.updatePosition(mc.player!!.x, mc.player!!.y+1,mc.player!!.z)
    }
}