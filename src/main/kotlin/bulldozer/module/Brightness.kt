package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Brightness : Module("Fullbright", emptyArray()) {
    private var oldgamma: Double = 1.0

    override fun onEnable(){
        oldgamma = mc.options.gamma
    }


    override fun onDisable() {
        mc.options.gamma = oldgamma
    }

    @Subscribe
    fun onTick(event: Tick) {
        if(!toggled) return
        if (mc.options.gamma < 16) mc.options.gamma += 2
    }
}