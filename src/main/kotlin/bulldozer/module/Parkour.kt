package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.util.math.Box


class Parkour : Module("Parkour", emptyArray<Any>(), true){
    @Subscribe
    fun onTick(event: Tick){
        if (!mc.player!!.isOnGround || mc.options.keyJump.isPressed || mc.options.keySneak.isPressed || !mc.options.keyForward.isPressed) return

        val box: Box = mc.player!!.boundingBox
        val adjustedBox: Box = box.offset(0.0, -0.5, 0.0).expand(
            -0.001,
            0.0,
            -0.001
        )

        val blockCollisions = mc.world!!.getBlockCollisions(mc.player, adjustedBox)

        if (blockCollisions.findAny().isPresent) return

        mc.player!!.jump()
    }
}