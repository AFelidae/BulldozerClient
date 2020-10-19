package bulldozer.module

import bulldozer.Module
import bulldozer.events.SendPacket
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.*;

class Headdesk: Module("Headdesk", arrayOf<Any>(SettingBoolean("Spinbot", false), SettingBoolean("DisableWhenClick", true)), true) {
    @Subscribe
    fun onTick(event: Tick){
        val click = mc.options.keyAttack.isPressed || mc.options.keyUse.isPressed
        val spin: Float = if((settings[0] as SettingBoolean).value) (Math.random() * 360 - 180).toFloat() else mc.player!!.yaw
        if(!((settings[1] as SettingBoolean).value && click))
            mc.networkHandler!!.sendPacket( LookOnly(spin, 90.0f, mc.player!!.isOnGround))
    }

    @Subscribe
    fun onSend(event: SendPacket){
        val click = mc.options.keyAttack.isPressed || mc.options.keyUse.isPressed
        if((settings[1] as SettingBoolean).value && click){
            if (event.packet !is LookOnly) mc.networkHandler!!.sendPacket(LookOnly(mc.player!!.yaw,mc.player!!.pitch,mc.player!!.isOnGround))
        } else {
            if (event.packet is LookOnly) {
                event.isCancelled()
            }
            if(event.packet is Both){
                var packet = event.packet as Both
                mc.networkHandler!!.sendPacket(PositionOnly(packet.getX(mc.player!!.x), packet.getY(mc.player!!.y), packet.getZ(mc.player!!.z), mc.player!!.isOnGround))
                event.isCancelled()
            }
        }
    }
}