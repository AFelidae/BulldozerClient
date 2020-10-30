package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class AutoTNT: Module("AutoTNT", emptyArray(), true){
    @Subscribe
    fun onTick(event: Tick){
        
    }
}