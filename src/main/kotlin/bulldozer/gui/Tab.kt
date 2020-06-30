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
        val width = mc.window.width

        var height = 30
        Screen.fill(matrix, 0, offset, width - (width % 50) - 50, offset+height, 0x70CCCCCC)
        mc.textRenderer.drawWithShadow(matrix, (if(collapsed) "+  " else "-  ") +  name,5.0f, (offset+8).toFloat(), 0xFFFFFF);

        if(!collapsed){
            for(mod in modules) {
                //render modules
                for (y in 0 .. (width / 50) %  modules.size) {
                    for (x in 0 until (width / 50) - 1) { //Leaves extra space for the settings panel
                        Screen.fill(matrix, 50 * x, offset + height, 50 * (x+1), offset + height + 20, 0x70222222)
                        if(y*x < modules.size){ //Check if module exists
                            mc.textRenderer.drawWithShadow(matrix, modules[y*x].name, 5.0f, (offset + height + 4).toFloat(), 0xFFFFFF);
                        }
                    }
                    height += 20;
                }
            }
        }
        return height
    }
}