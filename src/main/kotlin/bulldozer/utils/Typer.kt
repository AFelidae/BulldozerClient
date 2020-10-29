package bulldozer.utils

import net.minecraft.block.entity.*
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.decoration.ItemFrameEntity
import net.minecraft.entity.passive.CatEntity
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.vehicle.ChestMinecartEntity
import net.minecraft.entity.vehicle.FurnaceMinecartEntity
import net.minecraft.entity.vehicle.HopperMinecartEntity

object Typer {
    var mc: MinecraftClient = MinecraftClient.getInstance()

    fun entity(e: Entity) : EntityType{
        if(e == mc.player || e == mc.cameraEntity) return EntityType.USER
        if(e is PlayerEntity) return EntityType.PLAYER
        return EntityType.OTHER
    }
}