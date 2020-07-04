package bulldozer.utils

import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.passive.CatEntity
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.player.PlayerEntity

object Typer {
    var mc: MinecraftClient = MinecraftClient.getInstance()

    fun entity(e: Entity) : EntityType{
        if(e == mc.player || e == mc.cameraEntity) return EntityType.USER
        if (e is ItemEntity) return EntityType.ITEM
        if(mc.player!!.isTeammate(e)) return EntityType.TEAMMATE
        if(e is PlayerEntity) return EntityType.PLAYER
        if(e is WolfEntity)
            if(Friend.isFriend((e as WolfEntity).ownerUuid!!)) return EntityType.FRIEND
        if(e is CatEntity)
            if(Friend.isFriend((e as CatEntity).ownerUuid!!)) return EntityType.FRIEND
        if(e.type.spawnGroup.isAnimal) return EntityType.ANIMAL
        if(!e.type.spawnGroup.isPeaceful) return EntityType.MONSTER
        return EntityType.OTHER

    }

    fun container(b: Block){

    }
}