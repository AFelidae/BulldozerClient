package bulldozer.gui

import bulldozer.Manager
import bulldozer.Module
import bulldozer.module.Gui
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack

class Tab (title:String,children:ArrayList<Module>){
    val name = title
    val mc: MinecraftClient = MinecraftClient.getInstance()
    private val modules = children
    private var collapsed = true

    fun render(matrix: MatrixStack?, mouseX: Int, mouseY: Int, offset: Int, leftClick: Boolean, rightClick: Boolean): Int{
        val width = mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100)
        var height = 30
        val overHeader = hover(mouseX, mouseY, 0, offset, width, offset+30)
        if(overHeader && rightClick) collapsed = !collapsed

        Screen.fill(matrix, 0, offset, width, offset+30, if(overHeader) 0x70CCCCCC else 0x70AAAAAA)
        mc.textRenderer.drawWithShadow(matrix, (if(collapsed) "+  " else "-  ") +  name,5.0f, (offset+10).toFloat(), 0xFFFFFF);

        if(!collapsed){
            //render modules
            var x: Int = 0
            for(mod in modules){
                val overModule = hover(mouseX, mouseY, 100 * x, offset + height, 100 * (x+1), offset + height + 20)
                if(overModule){
                    if(leftClick){
                        mod.toggle()
                    }else if(rightClick){
                        var gui = Manager.getModule(Gui::class.java) as Gui
                        gui.selected = mod
                    }
                }
                if(mod.toggled) Screen.fill(matrix, 100 * x, offset + height, 100 * (x+1), offset + height + 20, if(overModule) 0x70CC4444 else 0x70CC2222)
                Screen.fill(matrix, 100 * x, offset + height, 100 * (x+1), offset + height + 20, if(overModule) 0x70444444 else 0x70222222)
                mc.textRenderer.drawWithShadow(matrix, mod.name, (100f * x) + 10, (offset + height + 6).toFloat(), 0xFFFFFF);
                x++
                if(x>(width/100)) {
                    x = 0
                    height += 20
                }
            }
            if(modules.size > 0) height += 20
        }
        return height
    }

    private fun hover(mouseX: Int, mouseY: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean{
        if(mouseX in x1..x2 && mouseY in y1..y2) return true
        return false
    }
}