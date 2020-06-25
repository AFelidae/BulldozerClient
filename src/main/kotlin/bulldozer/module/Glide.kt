package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Glide: Module("Glide", "Glide around the map", emptyArray()) {
    @Subscribe
    fun onTick(event: Tick){
        if(toggled){
            mc.player?.setVelocity(mc.player!!.velocity.x, 0.0, mc.player!!.velocity.z)
        }
    }
}