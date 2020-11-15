package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe
import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.text.LiteralText


class BanChests: Module("BanChests", arrayOf(SettingBoolean("Armorslot", false)), true) {
    @Subscribe
    fun onTick(event: Tick){
        if(!mc.player!!.isCreative) return

        val stack = ItemStack(Blocks.CHEST)
        val nbtCompound = CompoundTag()
        val nbtList = ListTag()
        for (i in 0..39999) nbtList.add(ListTag())
        nbtCompound.put("Too early for flapjacks?", nbtList)
        stack.tag = nbtCompound
        stack.setCustomName(LiteralText("Flapjacks"))

        if((settings[0] as SettingBoolean).value) {
            if(mc.player!!.inventory.getArmorStack(0).isEmpty) mc.player!!.inventory.armor[0] = stack;
        }else if(mc.player!!.inventory.getStack(mc.player!!.inventory.selectedSlot).isEmpty) mc.player!!.inventory.setStack(mc.player!!.inventory.selectedSlot, stack)
    }
}