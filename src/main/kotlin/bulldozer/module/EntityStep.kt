package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity

class EntityStep: Module("EntityStep", emptyArray<Any>()) {
    @Subscribe
    fun onTick(event: Tick) {
        if(!toggled) return
        var e: Entity? = mc.player!!.vehicle ?: return
        if(e!!.horizontalCollision) {
            e!!.updatePosition(e!!.x, e!!.y + 1, e!!.z)
        }
    }
}