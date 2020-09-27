package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingDouble
import com.google.common.eventbus.Subscribe
import bulldozer.utils.VectorUtil
import net.minecraft.util.math.Vec3d

class Flight3d: Module("Flight3d", arrayOf(
    SettingDouble("Speed", 1.0, 0.0, 3.0),
    SettingBoolean("Levitate", true))) {
    @Subscribe
    fun onTick(event: Tick){
        if(toggled){
            if(mc.options.keyForward.isPressed)
                mc.player?.velocity = VectorUtil.forwardVector3D().multiply((settings[0] as SettingDouble).value)
            else if((settings[1] as SettingBoolean).value){
                mc.player?.velocity = Vec3d(0.0,0.0,0.0)
            }
        }
    }
}