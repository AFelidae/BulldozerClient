package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket

class FallDamage: Module("FallDamage", emptyArray<Any>(), true) {
    @Subscribe
    fun onTick(event: Tick) {
        if (mc.player!!.fallDistance > 1.5f) {
            mc.player!!.networkHandler.sendPacket(PlayerMoveC2SPacket(true));
        }
    }
}