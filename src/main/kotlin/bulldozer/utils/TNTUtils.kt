package bulldozer.utils

import bulldozer.gui.SettingBoolean
import net.minecraft.block.Blocks
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt


object TNTUtils {
    var blacklist = HashSet<BlockPos>();

    fun joinLobby(){
        Chat.sendMessage("/play tnt_tntrun")
    }


    private fun donut(){
        val mc :MinecraftClient = MinecraftClient.getInstance()
        val c1 = BlockPos(mc.player!!.pos.x + 0.3, mc.player!!.pos.y-1, mc.player!!.pos.z + 0.3)
        val c2 = BlockPos(mc.player!!.pos.x - 0.3, mc.player!!.pos.y-1, mc.player!!.pos.z + 0.3)
        val c3 = BlockPos(mc.player!!.pos.x + 0.3, mc.player!!.pos.y-1, mc.player!!.pos.z - 0.3)
        val c4 = BlockPos(mc.player!!.pos.x - 0.3, mc.player!!.pos.y-1, mc.player!!.pos.z - 0.3)
        blacklist.add(c1)
        blacklist.add(c2)
        blacklist.add(c3)
        blacklist.add(c4)
    }

    private fun nearestPlayerDistance(blockPos: BlockPos) : Double? {
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
                if(blacklist.contains(blockPos.north(x).east(z))) continue
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
        val diffZ: Double = z - mc.player!!.z
        val yaw = Math.toDegrees(atan2(diffZ, diffX)).toFloat() - 90f
        mc.player!!.yaw += MathHelper.wrapDegrees(yaw - mc.player!!.yaw)
    }

    private fun drawFloor(blockPos: BlockPos, r: Float, g: Float, b: Float, a: Float){
        DrawUtil.drawBox(blockPos.x.toDouble(), blockPos.y.toDouble() + 1, blockPos.z.toDouble(), blockPos.x.toDouble() +1, blockPos.y.toDouble() + 1, blockPos.z.toDouble() + 1, r, g, b, a)
    }

    fun nextMove(visuals: Boolean): BlockPos?{
        var mc = MinecraftClient.getInstance()

        if(mc.player!!.isOnGround) {
            if(mc.world!!.getBlockState(mc.player!!.blockPos.down(1)).isAir){
                donut()
            }else{
                TNTUtils.blacklist.add(mc.player!!.blockPos.down(1))
            }
        }

        var bestWeight: Double? = null
        var bestPos: BlockPos? = null
        for(x in -4..4){
            for(z in -4..4) {
                val blockPos = mc.player!!.blockPos.down(1).north(x).east(z)
                val b = mc.world!!.getBlockState(blockPos)
                if(!(b.isOf(Blocks.SAND) || b.isOf(Blocks.GRAVEL) || b.isOf(Blocks.RED_SAND))) continue
                if(TNTUtils.blacklist.contains(blockPos) && visuals){
                    TNTUtils.drawFloor(blockPos, 1.0f, 0.0f, 0.0f, 0.3f)
                    continue
                }
                val weight = TNTUtils.getWeight(blockPos) - abs(x) - abs(z)
                if(visuals) TNTUtils.drawFloor(blockPos, 1.0f, 1.0f, 1.0f, 0.3f)
                if(x==0&&z==0) continue
                if(bestWeight == null){
                    bestPos = blockPos
                    bestWeight = weight
                }else{
                    if(bestWeight < weight){
                        bestWeight = weight
                        bestPos = blockPos
                    }
                }
            }
        }
        if(bestWeight != null)
            if(visuals) TNTUtils.drawFloor(bestPos!!, 0.0f, 0.0f, 1.0f, 0.3f)
        return bestPos
    }
}