package bulldozer.gui

import bulldozer.Module
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText

class Tab (title:String,children:ArrayList<Module>){
    val name = title
    val mc: MinecraftClient = MinecraftClient.getInstance()
    val modules = children
    var collapsed = false

    fun render(matrix: MatrixStack?, mouseX: Int, mouseY: Int, offset: Int): Int{
        val width = mc.window.width - 100 - (mc.window.width % 100)

        var height = 30
        Screen.fill(matrix, 0, offset, width, offset+30, 0x70CCCCCC)
        mc.textRenderer.drawWithShadow(matrix, (if(collapsed) "+  " else "-  ") +  name,5.0f, (offset+10).toFloat(), 0xFFFFFF);

        if(!collapsed){
                //render modules
            if(modules.size > 0) height += 20
            var x: Int = 0
            for(mod in modules){
                Screen.fill(matrix, 100 * x, offset + height, 100 * (x+1), offset + height + 20, 0x70222222)
                mc.textRenderer.drawWithShadow(matrix, mod.name, 5.0f, (offset + height + 5).toFloat(), 0xFFFFFF);
                x++
                if(x>(width/100)) {
                    x = 0
                    height += 20
                }
            }

            /*
            for (y in 0 .. (width / 50) %  modules.size) {
                for (x in 0 until (width / 50)) { //Leaves extra space for the settings panel
                    Screen.fill(matrix, 50 * x, offset + height, 50 * (x+1), offset + height + 20, 0x70222222)
                    if(y*x < modules.size){ //Check if module exists
                        mc.textRenderer.drawWithShadow(matrix, modules[y*x].name, 5.0f, (offset + height + 4).toFloat(), 0xFFFFFF);
                    }
                }
                height += 20;
            }*/
        }
        return height
    }
}