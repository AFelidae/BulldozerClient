package bulldozer.module

import bulldozer.Module
import bulldozer.events.KeyPress
import com.google.common.eventbus.Subscribe

class AirJump: Module("AirJump", emptyArray<Any>(), true){
    @Subscribe
    fun onKeyPress(event: KeyPress){
        if(mc.options.keyJump.matchesKey(event.key, 0)){
            if(mc.player != null){
                if(!mc.player!!.isOnGround) mc.player!!.jump()
            }
        }
    }
}