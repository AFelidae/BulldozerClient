package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingMode
import com.google.common.eventbus.Subscribe
import net.minecraft.item.CrossbowItem
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.SlotActionType

class CrossbowReload : Module("CrossbowReload", arrayOf<Any>(SettingMode("Mode", arrayOf("Offhand", "Mainhand", "Hotbar"))), true) {
    @Subscribe
    fun onTick(event: Tick) { //TODO: add creative mode
        when ((settings[0] as SettingMode).value) {
            0 -> { //Offhand
                if (isLoadedCrossbow(mc.player!!.offHandStack)) return
                for (i in 0..35) {
                    if (isLoadedCrossbow(mc.player!!.inventory.getStack(i))) {
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            if (i < 9) i + 36 else i,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            45,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            if (i < 9) i + 36 else i,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        return
                    }
                }
            }
            1 -> { //Main hand
                if (isLoadedCrossbow(mc.player!!.mainHandStack)) return
                for (i in 0..35) {
                    if (isLoadedCrossbow(mc.player!!.inventory.getStack(i))) {
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            if (i < 9) i + 36 else i,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            mc.player!!.inventory.selectedSlot,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        mc.interactionManager!!.clickSlot(
                            mc.player!!.currentScreenHandler.syncId,
                            if (i < 9) i + 36 else i,
                            0,
                            SlotActionType.PICKUP,
                            mc.player
                        )
                        return
                    }
                }
            }
            else -> {
                if (isLoadedCrossbow(mc.player!!.mainHandStack)) return
                for(i in 0..8){
                    if (isLoadedCrossbow(mc.player!!.inventory.getStack(i))) {
                        mc.player!!.inventory.selectedSlot = i
                        return
                    }
                }
            }
        }
    }

    private fun isLoadedCrossbow(item: ItemStack): Boolean{
        if(item.item is CrossbowItem && item.tag != null){
            if(item.tag!!.getBoolean("Charged")){
                return true
            }
            return false
        }
        return false
    }
}