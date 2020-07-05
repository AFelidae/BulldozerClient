package bulldozer.utils

import net.minecraft.block.*
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
        if(e is ItemEntity) return EntityType.ITEM
        if(!e.isAttackable) return EntityType.OTHER
        if(mc.player!!.isTeammate(e)) return EntityType.TEAMMATE
        if(e is ChestMinecartEntity || e is FurnaceMinecartEntity || e is HopperMinecartEntity || e is ItemFrameEntity) return EntityType.STORAGE
        if(e is PlayerEntity) return EntityType.PLAYER
        if(e is WolfEntity)
            if(Friend.isFriend((e as WolfEntity).ownerUuid)) return EntityType.FRIEND
        if(e is CatEntity)
            if(Friend.isFriend((e as CatEntity).ownerUuid)) return EntityType.FRIEND
        if(e.type.spawnGroup.isAnimal) return EntityType.ANIMAL
        if(!e.type.spawnGroup.isPeaceful) return EntityType.MONSTER
        return EntityType.OTHER

    }

    fun container(b: Block): ContainerType{
        if(b is ShulkerBoxBlock) return ContainerType.SHULKER
        if(b is ChestBlock || b is TrappedChestBlock || b is BarrelBlock) return ContainerType.CHEST
        if(b is DispenserBlock || b is DropperBlock || b is HopperBlock) return ContainerType.REDSTONE
        if(b is FurnaceBlock || b is BlastFurnaceBlock || b is SmokerBlock || b is EnchantingTableBlock || b is BrewingStandBlock) return ContainerType.OTHER
        if(b is EnderChestBlock) return ContainerType.ENDERCHEST
        return ContainerType.NOT
    }
}