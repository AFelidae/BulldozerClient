package bulldozer.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.Vec3d
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object VectorUtil {
    private var mc: MinecraftClient = MinecraftClient.getInstance()

    fun forwardVector2D(): Vec3d {
        var product: Vec3d = Vec3d(0.0,0.0,1.0)
        product = product.rotateY(-(Math.toRadians(mc.player!!.headYaw.toDouble()).toFloat()))
        return product
    }

    fun forwardVector3D(): Vec3d {
        var base = forwardVector2D().add(0.0, 1.0, 0.0)
        val angle = Math.toRadians(mc.player!!.pitch.toDouble()- Math.PI/2)
        base = base.multiply(abs(cos(angle)), -sin(angle), abs(cos(angle)))
        return base
    }
}