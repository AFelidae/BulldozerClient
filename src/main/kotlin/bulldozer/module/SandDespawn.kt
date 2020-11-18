package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.render.entity.FallingBlockEntityRenderer
import net.minecraft.entity.FallingBlockEntity

class SandDespawn: Module("SandDespawn", emptyArray<Any>(), true) {
    @Subscribe
    fun onTick(event: Tick){
        for(e in mc.world!!.entities){
            if(e is FallingBlockEntity) {
                e.isInvisible = true
                e.remove()
                e.kill()
            }
        }
    }
}