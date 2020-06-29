package bulldozer.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import java.util.UUID


object Friend {
    var mc: MinecraftClient = MinecraftClient.getInstance()
    var friends: ArrayList<UUID> = ArrayList<UUID>()

    fun isFriend(e: Entity): Boolean{
        if(mc.player == e) return true
        return isFriend(e.uuid)
    }

    fun isFriend(uuid: UUID): Boolean{
        if(mc.player!!.uuid.equals(uuid)) return true
        for(u in friends)
            if(u.equals(uuid)) return true
        return false
    }
}