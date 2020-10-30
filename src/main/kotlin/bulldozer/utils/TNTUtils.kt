package bulldozer.utils

import net.minecraft.block.Blocks
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import kotlin.math.atan2
import kotlin.math.sqrt


object TNTUtils {
    var blacklist = HashMap<BlockPos, Boolean>(); //I swear theres some data structure that you can just check if its in it but its not a hashmap

    fun joinLobby(){
        Chat.sendMessage("/play tnt_tntrun")
    }

    fun nearestPlayerDistance(blockPos: BlockPos) : Double? {
        val mc :MinecraftClient = MinecraftClient.getInstance()
        var nearest: Double? = null
        for(e in mc.world!!.entities){
            if(e is PlayerEntity && e != mc.player){
                val distance = sqrt(
                    ((blockPos.x + 0.5 - e.x) * (blockPos.x + 0.5 - e.x)) +
                            ((blockPos.y + 0.5 - e.y) * (blockPos.y + 0.5 - e.y)) +
                            ((blockPos.z + 0.5 - e.z) * (blockPos.z + 0.5 - e.z))
                )
                if(nearest != null){
                    if(distance < nearest) nearest = distance
                }else{
                    nearest = distance
                }
            }
        }
        return nearest
    }

    fun getWeight(blockPos: BlockPos): Double{
        var mc = MinecraftClient.getInstance()
        var weight = 0.0
        for(x in -4..4){
            for(z in -4..4){
                //Will count itself and have a value of 1 at minimum
                val b = mc.world!!.getBlockState(blockPos.north(x).east(z))
                if(blacklist[blockPos.north(x).east(z)] != null) continue
                if(b.isOf(Blocks.SAND) || b.isOf(Blocks.GRAVEL) || b.isOf(Blocks.RED_SAND)) weight++
            }
        }

        var distance = nearestPlayerDistance(blockPos)
        if(distance != null) weight -= 10/distance

        return weight;
    }

    fun lookAt(x: Double, y: Double, z: Double) {
        var mc = MinecraftClient.getInstance()
        val diffX: Double = x - mc.player!!.x
        val diffY: Double = y - (mc.player!!.y + mc.player!!.getEyeHeight(mc.player!!.pose))
        val diffZ: Double = z - mc.player!!.z
        val diffXZ = sqrt(diffX * diffX + diffZ * diffZ)
        val yaw = Math.toDegrees(atan2(diffZ, diffX)).toFloat() - 90f
        val pitch = (-Math.toDegrees(atan2(diffY, diffXZ))).toFloat()
        mc.player!!.yaw += MathHelper.wrapDegrees(yaw - mc.player!!.yaw)
        mc.player!!.pitch += MathHelper.wrapDegrees(pitch - mc.player!!.pitch)
    }

    fun drawFloor(blockPos: BlockPos, r: Float, g: Float, b: Float, a: Float){
        DrawUtil.drawBox(blockPos.x.toDouble(), blockPos.y.toDouble() + 1, blockPos.z.toDouble(), blockPos.x.toDouble() +1, blockPos.y.toDouble() + 1, blockPos.z.toDouble() + 1, r, g, b, a)
    }
}