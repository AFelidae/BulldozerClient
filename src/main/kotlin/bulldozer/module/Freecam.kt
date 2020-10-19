package bulldozer.module

import bulldozer.Module
import bulldozer.events.ClientMove
import bulldozer.events.SendPacket
import bulldozer.events.Tick
import bulldozer.gui.SettingDouble
import bulldozer.utils.VectorUtil
import com.google.common.eventbus.Subscribe
import net.minecraft.client.network.OtherClientPlayerEntity
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import net.minecraft.util.math.Vec3d


class Freecam: Module("Freecam", arrayOf(
    SettingDouble("Speed", 2.0, 0.0, 5.0)
), false) {
    private var freecamEntity: OtherClientPlayerEntity? = null // OtherClientPlayerEntity(mc.world,mc.player!!.gameProfile)

    override fun onEnable() {
        freecamEntity = OtherClientPlayerEntity(mc.world, mc.player!!.gameProfile)
        freecamEntity!!.copyPositionAndRotation(mc.player)
        mc.world!!.addEntity(freecamEntity!!.entityId, freecamEntity)
    }

    override fun onDisable() {
        mc.player!!.copyPositionAndRotation(freecamEntity)
        mc.world!!.removeEntity(freecamEntity!!.entityId)
        freecamEntity = null
    }

    @Subscribe
    fun onSend(event: SendPacket) {
        if(!toggled) return
        if (event.packet is ClientCommandC2SPacket || event.packet is PlayerMoveC2SPacket) {
            event.setCancelled(true)
        }
    }

    @Subscribe
    fun onClientMove(event: ClientMove) {
        if(!toggled) return
        mc.player!!.noClip = true
    }

    @Subscribe
    fun onTick(event: Tick){
        if(toggled && freecamEntity != null){
            val speed: Double = (settings[0] as SettingDouble).value
            var freecamVel = Vec3d(0.0, 0.0, 0.0) //TODO: replace all instances of this with Vec3d.ZERO
            if(mc.options.keyForward.isPressed)
                freecamVel = freecamVel.add(VectorUtil.forwardVector2D())
            if(mc.options.keyBack.isPressed)
                freecamVel = freecamVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()).multiply(speed))
            if(mc.options.keyLeft.isPressed)
                freecamVel = freecamVel.add(VectorUtil.forwardVector2D().rotateY(Math.PI.toFloat()/2).multiply(speed))
            if(mc.options.keyRight.isPressed)
                freecamVel = freecamVel.add(VectorUtil.forwardVector2D().rotateY(-Math.PI.toFloat()/2).multiply(speed))
            if(mc.options.keySneak.isPressed)
                freecamVel = freecamVel.add(0.0,-speed,0.0)
            if(mc.options.keyJump.isPressed)
                freecamVel = freecamVel.add(0.0,speed,0.0)
            mc.player!!.velocity = freecamVel;
        }
    }
}