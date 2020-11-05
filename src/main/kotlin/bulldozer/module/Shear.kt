package bulldozer.module

import bulldozer.events.Tick
import bulldozer.gui.SettingDouble
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity
import net.minecraft.util.Hand
import kotlin.math.pow
import kotlin.math.sqrt

import bulldozer.Module
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket
class Shear : Module("Shear", arrayOf<Any>(SettingDouble("Range", 3.5, 2.0, 6.0)), true){
    private fun getDistance(entity: Entity): Double{
        if(entity.y > mc.player!!.eyeY){ //Above player

            return sqrt((entity.x - mc.player!!.x).pow(2) +
                    (entity.z - mc.player!!.z).pow(2) +
                    (entity.y - mc.player!!.eyeY).pow(2))

        }else if(entity.y + entity.height < mc.player!!.eyeY){ //Below player

            return sqrt((entity.x - mc.player!!.x).pow(2) +
                    (entity.z - mc.player!!.z).pow(2) +
                    (entity.y + entity.height - mc.player!!.eyeY).pow(2))

        } else { //Level with player

            return sqrt((entity.x - mc.player!!.x).pow(2) +
                    (entity.z - mc.player!!.z).pow(2))

        }
    }

    @Subscribe
    fun onTick(event: Tick){
        val offhand = (mc.player!!.getStackInHand(Hand.OFF_HAND).isItemEqual(ItemStack(Items.SHEARS)))
        val mainhand = (mc.player!!.getStackInHand(Hand.MAIN_HAND).isItemEqual(ItemStack(Items.SHEARS)))
        if(!(offhand || mainhand)) return

        val range = (settings[0] as SettingDouble).value
        for(e in mc.world!!.entities){
            if(e is SheepEntity){
                val s = e as SheepEntity
                val distance = getDistance(e)
                if(distance < range){
                    if(s.isShearable) {
                        if(mainhand) mc.networkHandler!!.sendPacket(PlayerInteractEntityC2SPacket(e, Hand.MAIN_HAND, mc.player!!.isSneaking))
                        else if(offhand) mc.networkHandler!!.sendPacket(PlayerInteractEntityC2SPacket(e, Hand.OFF_HAND, mc.player!!.isSneaking))
                    }
                } else continue
            }
        }
    }
}