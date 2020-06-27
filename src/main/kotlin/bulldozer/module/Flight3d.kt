package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.Setting
import com.google.common.eventbus.Subscribe
import bulldozer.utils.VectorUtil
import net.minecraft.util.math.Vec3d

class Flight3d: Module("flight3d", "Fly at where you aim", arrayOf(
    Setting("speed", 1.0, 0.0, 3.0),
    Setting("levitate", true))) {
    @Subscribe
    fun onTick(event: Tick){
        if(toggled){
            if(mc.options.keyForward.isPressed)
                mc.player?.velocity = VectorUtil.forwardVector3D().multiply(settings[0].value as Double)
            else if(settings[1].value as Boolean){
                mc.player?.velocity = Vec3d(0.0,0.0,0.0)
            }
        }
    }
}