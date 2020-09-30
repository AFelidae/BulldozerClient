package bulldozer.module

import bulldozer.Module
import bulldozer.events.SendPacket
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.*;

class Headdesk: Module("Headdesk", emptyArray<Any>()) {
    @Subscribe
    fun onTick(event: Tick){
        if(!toggled) return
        mc.networkHandler!!.sendPacket( LookOnly(mc.player!!.yaw, 90.0f, mc.player!!.isOnGround))
    }

    @Subscribe
    fun onSend(event: SendPacket){
        if(!toggled) return
        if (event.packet is LookOnly) {
            event.isCancelled()
        }
        if(event.packet is Both){
            var packet = event.packet as Both
            mc.networkHandler!!.sendPacket(PositionOnly(packet.getX(mc.player!!.x), packet.getY(mc.player!!.y), packet.getZ(mc.player!!.z), mc.player!!.isOnGround))
            event.isCancelled()
        }
    }
    //TODO: add an item use event and for a moment use the correct rotation while an item is being used ie, ender pearl.
}