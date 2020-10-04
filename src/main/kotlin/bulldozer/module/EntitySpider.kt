package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity

class EntitySpider: Module("EntitySpider", emptyArray<Any>()) {
    @Subscribe
    fun onTick(event: Tick){
        if(!toggled || !mc.options.keyForward.isPressed) return
        var e: Entity? = mc.player!!.vehicle ?: return
        if(e!!.horizontalCollision) e.setVelocity(e.velocity.x, 0.3, e.velocity.z)
    }
}