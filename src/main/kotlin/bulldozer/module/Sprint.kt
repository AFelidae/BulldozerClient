package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Sprint : Module("Sprint", emptyArray<Any>(), true){
    @Subscribe
    fun onTick(event: Tick){
        if(!mc.player!!.isSprinting && mc.options.keyForward.isPressed) mc.player!!.isSprinting = true
    }
}