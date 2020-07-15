package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe

class Strafe: Module("Strafe", emptyArray<Any>()) {
    private var oldRot = 0f

    @Subscribe
    fun onTick(event: Tick) {
        var newRot = Math.toRadians(mc.player!!.getHeadYaw().toDouble()).toFloat()
        if(toggled) {
            //Rotate 45 deg
            if (mc.options.keyLeft.isPressed) newRot -= 0.7854f
            if (mc.options.keyRight.isPressed) newRot += 0.7854f
            mc.player!!.velocity = mc.player!!.velocity.rotateY(oldRot - newRot)
        }
        oldRot = newRot
    }
}