package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Walk: Module("Walk", emptyArray(), true) {
    @Subscribe
    fun onTick(event: Tick){
        mc.options.keyForward.isPressed = true
    }
}