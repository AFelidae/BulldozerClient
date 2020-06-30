package bulldozer.mixins;

import bulldozer.Command;
import bulldozer.Manager;
import bulldozer.Module;
import bulldozer.events.KeyPress;
import bulldozer.gui.ClickGui;
import bulldozer.utils.Chat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.Keyboard.class)
public class Keyboard {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo callback) {
        if(Manager.binding) {
            Manager.binding = false;
            Module m = Manager.getModuleByName(Manager.bindname);
            //if (m != null) Chat.errorMessage("The name of the module is wrong :(");
            //else
            try {
                m.setKey(key);
            } catch(Exception err){
                Chat.errorMessage("The name of the module is wrong :(");
            }
        } else if (key == 344) { //Right Shift
            //toggle gui
            MinecraftClient.getInstance().openScreen(new ClickGui());
            callback.cancel();
        } else if (key == 45) { // Minus
            MinecraftClient.getInstance().openScreen(new ChatScreen("-"));
            callback.cancel();
        } else {
            KeyPress event = new KeyPress(key);
            Manager.eventSystem.post(event);
            if (event.isCancelled()) callback.cancel();
        }
    }
}