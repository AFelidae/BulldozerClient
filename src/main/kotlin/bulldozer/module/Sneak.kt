package bulldozer.module

import bulldozer.Module;
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingMode
import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket

class Sneak: Module("Sneak", arrayOf(SettingMode("Mode", arrayOf("Packet", "Input")),SettingBoolean("LedgeHop", false))) {
    override fun onDisable() {
        mc.player!!.input.sneaking = false
        mc.player!!.isSneaking = false
        mc.player!!.networkHandler.sendPacket(
            ClientCommandC2SPacket(
                mc.player,
                ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY
            )
        )
        super.onDisable()
    }

    @Subscribe
    fun onTick(event: Tick) {
        if (toggled) {
            if(mc.player!!.isOnGround && mc.world!!.getBlockState(mc.player!!.blockPos.down(1)).isAir && (settings[1] as SettingBoolean).value){
                mc.player!!.jump()
            }
            if ((settings[0] as SettingMode).value == 1) mc.player!!.input.sneaking = true
            if ((settings[0] as SettingMode).value == 0) mc.player!!.networkHandler.sendPacket(
                ClientCommandC2SPacket(
                    mc.player,
                    ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY
                )
            )
        }
    }
}