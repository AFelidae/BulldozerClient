package bulldozer.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.Vec3d

object VectorUtil {
    private var mc: MinecraftClient = MinecraftClient.getInstance()

    fun forwardVector2D(): Vec3d {
        var product: Vec3d = Vec3d(0.0,0.0,1.0)
        product = product.rotateY(-(Math.toRadians(mc.player!!.headYaw.toDouble()).toFloat()))
        return product
    }

    fun forwardVector3D(): Vec3d {
        return Vec3d(0.0, 0.0, 1.0)
            .rotateX((-Math.toRadians(mc.cameraEntity!!.pitch.toDouble())).toFloat())
            .rotateY((-Math.toRadians(mc.cameraEntity!!.yaw.toDouble())).toFloat())
    }
}