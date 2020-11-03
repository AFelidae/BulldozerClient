package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe
import net.minecraft.util.math.Box

class Jump : Module("Jump", arrayOf<Any>(SettingBoolean("OnlyUnderBlock", false)), true){
    @Subscribe
    fun onTick(event: Tick) {
        if (!mc.player!!.isOnGround) return
        if ((settings[0] as SettingBoolean).value && (mc.world!!.getBlockState(mc.player!!.blockPos.up(1)).isAir || mc.world!!.getBlockState(mc.player!!.blockPos.up(2)).isAir)) {
            mc.player!!.jump()
        } else mc.player!!.jump()
    }
}