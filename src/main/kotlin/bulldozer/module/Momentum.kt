package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingDouble
import com.google.common.eventbus.Subscribe
import net.minecraft.util.math.Vec3d
import kotlin.math.abs

class Momentum: Module("Momentum", arrayOf(SettingDouble("Conserved", 0.5, 0.0,1.0), SettingBoolean("DoY",false)), true) {
    private var lastMove: Vec3d? =  Vec3d.ZERO
    @Subscribe
    fun onTick(event: Tick){
        val multiplier = (settings[0] as SettingDouble).value
        var x = mc.player!!.velocity.x
        var y = mc.player!!.velocity.y
        var z = mc.player!!.velocity.z
        if (abs(lastMove!!.x) > abs(mc.player!!.velocity.x)) {
            x = (mc.player!!.velocity.x * (1-multiplier)) + (lastMove!!.x * (multiplier))
        }

        if (abs(lastMove!!.y) > abs(mc.player!!.velocity.y) && (settings[1] as SettingBoolean).value) {
            y = (mc.player!!.velocity.y * (1 - multiplier)) + (lastMove!!.y * (multiplier))
        }

        if (abs(lastMove!!.z) > abs(mc.player!!.velocity.z)) {
                z = (mc.player!!.velocity.z * (1 - multiplier)) + (lastMove!!.z * (multiplier))
        }
        mc.player!!.setVelocity(x,y,z)

        lastMove = mc.player!!.velocity
    }
}