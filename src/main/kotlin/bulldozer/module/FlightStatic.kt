package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingDouble
import com.google.common.eventbus.Subscribe
import bulldozer.utils.VectorUtil
import net.minecraft.util.math.Vec3d

class FlightStatic: Module("FlightStatic", arrayOf(SettingDouble("speed", 1.0, 0.0, 3.0)), true) {
    @Subscribe
    fun onTick(event: Tick){
        val speed: Double = (settings[0] as SettingDouble).value
        var newVel = Vec3d(0.0, 0.0, 0.0)
        if(mc.options.keyForward.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().multiply(speed))
        if(mc.options.keyBack.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()).multiply(speed))
        if(mc.options.keyLeft.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()/2).multiply(speed))
        if(mc.options.keyRight.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(-Math.PI.toFloat()/2).multiply(speed))
        if(mc.options.keySneak.isPressed)
            newVel = newVel.add(0.0,-speed,0.0)
        if(mc.options.keyJump.isPressed)
            newVel = newVel.add(0.0,speed,0.0)
        mc.player!!.velocity = newVel
    }
}
