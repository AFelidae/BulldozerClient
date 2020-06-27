package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.Setting
import com.google.common.eventbus.Subscribe
import bulldozer.utils.VectorUtil
import net.minecraft.util.math.Vec3d

class FlightStatic: Module("flightstatic", "Fly around the map", arrayOf(Setting("speed", 1.0, 0.0, 3.0))) {
    @Subscribe
    fun onTick(event: Tick){
        if(toggled){
            val speed: Double = settings[0].value as Double
            var newVel = Vec3d(0.0, 0.0, 0.0)
            if(mc.options.keyForward.isPressed)
                newVel.add(VectorUtil.forwardVector2D())
            if(mc.options.keyBack.isPressed)
                newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()).multiply(speed))
            if(mc.options.keyLeft.isPressed)
                newVel.add(VectorUtil.forwardVector2D().rotateY(-Math.PI.toFloat()/2).multiply(speed))
            if(mc.options.keyRight.isPressed)
                newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()/2).multiply(speed))
            if(mc.options.keySneak.isPressed)
                newVel.add(0.0,-speed,0.0)
            if(mc.options.keyJump.isPressed)
                newVel.add(0.0,speed,0.0)
            mc.player!!.velocity = newVel
        }
    }
}
