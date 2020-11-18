package bulldozer.module

import bulldozer.Module
import net.minecraft.entity.Entity

class EntityDesync: Module("EntityDesync", emptyArray<Any>(), true) {
    private var riding: Entity? = null

    override fun onDisable() {
        super.onDisable()
        if(riding != null){
            riding!!.removed = false
            mc.world!!.addEntity(riding!!.entityId, riding);
            mc.player!!.startRiding(riding, true);
            riding = null
        }
    }

    override fun onEnable() {
        super.onEnable()
        if(mc.player!!.vehicle != null) {
            riding = mc.player!!.vehicle;
            mc.player!!.stopRiding();
            mc.world!!.removeEntity(riding!!.entityId);
        }
    }
}