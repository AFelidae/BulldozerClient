package bulldozer.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL14

object DrawUtil {
    fun drawBox(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double, r: Float, g: Float, b: Float, a: Float) {
        gl11Setup()
        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        buffer.begin(5, VertexFormats.POSITION_COLOR)
        WorldRenderer.drawBox(
            buffer,
            x1, y1, z1,
            x2, y2, z2, r, g, b, a
        )
        tessellator.draw()

        // Outline
        buffer.begin(3, VertexFormats.POSITION_COLOR)
        buffer.vertex(x1, y1, z1).color(r, g, b, a).next()
        buffer.vertex(x1, y1, z2).color(r, g, b, a).next()
        buffer.vertex(x2, y1, z2).color(r, g, b, a).next()
        buffer.vertex(x2, y1, z1).color(r, g, b, a).next()
        buffer.vertex(x1, y1, z1).color(r, g, b, a).next()
        buffer.vertex(x1, y2, z1).color(r, g, b, a).next()
        buffer.vertex(x2, y2, z1).color(r, g, b, a).next()
        buffer.vertex(x2, y2, z2).color(r, g, b, a).next()
        buffer.vertex(x1, y2, z2).color(r, g, b, a).next()
        buffer.vertex(x1, y2, z1).color(r, g, b, a).next()
        buffer.vertex(x1, y1, z2).color(r, g, b, 0f).next()
        buffer.vertex(x1, y2, z2).color(r, g, b, a).next()
        buffer.vertex(x2, y1, z2).color(r, g, b, 0f).next()
        buffer.vertex(x2, y2, z2).color(r, g, b, a).next()
        buffer.vertex(x2, y1, z1).color(r, g, b, 0f).next()
        buffer.vertex(x2, y2, z1).color(r, g, b, a).next()
        tessellator.draw()
        gl11Cleanup()
    }

    fun blockBox(
        blockPos: BlockPos, r: Float, g: Float, b: Float, a: Float) {
        drawBox(blockPos.x.toDouble(),blockPos.y.toDouble(),blockPos.z.toDouble(),(blockPos.x+1).toDouble(),(blockPos.y+1).toDouble(),(blockPos.z+1).toDouble(),
            r, g, b, a)
    }

    fun entityBox(entity: Entity, r: Float, g: Float, b: Float, a: Float){
        drawBox(entity.boundingBox.minX,entity.boundingBox.minY,entity.boundingBox.minZ,entity.boundingBox.maxX,entity.boundingBox.maxY,entity.boundingBox.maxZ,r,g,b,a)
    }
    

    fun drawLine(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double, r: Float, g: Float, b: Float, a: Float, t: Float) {
        gl11Setup()
        GL11.glLineWidth(t)
        val tessellator = Tessellator.getInstance()
        val buffer = tessellator.buffer
        buffer.begin(3, VertexFormats.POSITION_COLOR)
        buffer.vertex(x1, y1, z1).color(r, g, b, 0.0f).next()
        buffer.vertex(x1, y1, z1).color(r, g, b, a).next()
        buffer.vertex(x2, y2, z2).color(r, g, b, a).next()
        tessellator.draw()
        gl11Cleanup()
    }

    fun entityLine(entity: Entity, r: Float, g:Float, b:Float, a:Float, t:Float){
        val mc :MinecraftClient = MinecraftClient.getInstance()
        val forward = VectorUtil.forwardVector3D()
        val towardX = forward.x * 4
        val towardY = forward.y * 4
        val towardZ = forward.z * 4


        drawLine(mc.player!!.x + towardX, mc.player!!.eyeY + forward.y, mc.player!!.z + towardZ, entity.x, entity.y, entity.z, r, g, b, a, t)
    }

    private fun offsetRender() {
        val camera = BlockEntityRenderDispatcher.INSTANCE.camera
        val camPos = camera.pos
        GL11.glRotated(MathHelper.wrapDegrees(camera.pitch).toDouble(), 1.0, 0.0, 0.0)
        GL11.glRotated(MathHelper.wrapDegrees(camera.yaw + 180.0), 0.0, 1.0, 0.0)
        GL11.glTranslated(-camPos.x, -camPos.y, -camPos.z)
    }

    private fun gl11Setup() {
        GL11.glEnable(GL11.GL_BLEND)
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO)
        GL11.glLineWidth(2.5f)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glMatrixMode(5889)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)
        GL11.glPushMatrix()
        offsetRender()
    }

    private fun gl11Cleanup() {
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
        GL11.glPopMatrix()
        GL11.glMatrixMode(5888)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_BLEND)
    }
}