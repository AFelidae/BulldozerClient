package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingDouble
import bulldozer.utils.VectorUtil
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d

class EntitySpeed: Module("EntitySpeed", arrayOf<Any>(SettingBoolean("EntityFlight", false),SettingDouble("HorizontalSpeed", 1.0, 0.0, 5.0),SettingDouble("AscentSpeed",1.0, 0.5, 2.0),SettingDouble("DescendSpeed", 0.2, 0.0, 1.0))){
    @Subscribe
    fun onTick(event: Tick){
        if(!toggled) return
        var e: Entity? = mc.player!!.vehicle ?: return

        val speed: Double = (settings[1] as SettingDouble).value
        val up: Double = (settings[2] as SettingDouble).value
        val down: Double = (settings[3] as SettingDouble).value

        var newVel = Vec3d(0.0, 0.0, 0.0)
        if(mc.options.keyForward.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().multiply(speed))
        if(mc.options.keyBack.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()).multiply(speed))
        if(mc.options.keyLeft.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()/2).multiply(speed))
        if(mc.options.keyRight.isPressed)
            newVel = newVel.add(VectorUtil.forwardVector2D().rotateY(-Math.PI.toFloat()/2).multiply(speed))
        if((settings[0] as SettingBoolean).value) {
            if (mc.options.keyJump.isPressed)
                newVel = newVel.add(0.0, up, 0.0)
            else if (!e!!.isOnGround)
                newVel = newVel.add(0.0, -down, 0.0)
        }else{
            newVel = newVel.add(0.0, e!!.velocity.y, 0.0)
        }
        e!!.velocity = newVel
    }
}