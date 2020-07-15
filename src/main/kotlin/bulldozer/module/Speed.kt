package bulldozer.module

import bulldozer.Module
import bulldozer.events.ClientMove
import bulldozer.events.SendPacket
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingDouble
import bulldozer.gui.SettingFloat
import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket

class Speed: Module("Speed", emptyArray<Any>()) {
    @Subscribe
    fun onTick(event: Tick) {
        if(!toggled || mc.options.keyJump.isPressed || !mc.player!!.isSprinting) return
        if(mc.player!!.isOnGround) {
            mc.player!!.jump()
            mc.player!!.setVelocity(mc.player!!.velocity.x, 0.0, mc.player!!.velocity.z)
        }
    }
}