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

class Spider: Module("Spider", emptyArray<Any>()) {
    @Subscribe
    fun onTick(event: Tick) {
        if(!toggled) return
        if(mc.player!!.horizontalCollision && mc.options.keyForward.isPressed){
            mc.player!!.setVelocity(mc.player!!.velocity.x, 0.3, mc.player!!.velocity.z)
        }
    }
}