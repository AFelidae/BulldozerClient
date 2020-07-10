package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingDouble
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket
import net.minecraft.util.Hand
import kotlin.math.pow
import kotlin.math.sqrt


class KillAura : Module("KillAura", arrayOf(
    SettingBoolean("Animal", false),
    SettingBoolean("Monster", false),
    SettingBoolean("Player", true),
    SettingBoolean("Other", false),
    SettingBoolean("HitInvisible", true),
    SettingBoolean("HitTeammate", true),
    SettingBoolean("HitFriend", true),
    SettingDouble("Range", 3.8, 0.0, 6.0))){

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
        if(!toggled) return

        if(mc.player!!.getAttackCooldownProgress(mc.tickDelta) != 1f) return

        var closest: Entity? = null;
        var range = (settings[7] as SettingDouble).value

        for(e in mc.world!!.entities){
            var failed = false

            if(!e.isAttackable) continue
            when(Typer.entity(e)){
                EntityType.ANIMAL -> {
                    if(!(settings[0] as SettingBoolean).value) failed = true
                }
                EntityType.MONSTER -> {
                    if(!(settings[1] as SettingBoolean).value) failed = true
                }
                EntityType.PLAYER -> {
                    if(!(settings[2] as SettingBoolean).value) failed = true
                }
                EntityType.OTHER -> {
                    if(!(settings[3] as SettingBoolean).value) failed = true
                }
                EntityType.TEAMMATE -> {
                    if(!((settings[5] as SettingBoolean).value)) failed = true
                }
                EntityType.FRIEND -> {
                    if(!(settings[6] as SettingBoolean).value) failed = true
                }
                EntityType.USER -> {
                    failed = true
                }
            }
            if(e.isInvisible && !(settings[4] as SettingBoolean).value) failed = true
            if(failed) continue

            val distance = getDistance(e)
            if(distance < range){
                range = distance
                closest = e
            } else continue
        }

        if(closest == null) return

        mc.player!!.networkHandler.sendPacket(PlayerInteractEntityC2SPacket(closest, mc.player!!.isSneaking))
        mc.player!!.attack(closest)
        mc.player!!.swingHand(Hand.MAIN_HAND)

    }
}