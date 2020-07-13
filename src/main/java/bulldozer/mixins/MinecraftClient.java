package bulldozer.mixins;


import bulldozer.Manager;
import bulldozer.events.OpenScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.MinecraftClient.class)
public class MinecraftClient {
    @Inject(at = @At("HEAD"), method = "openScreen(Lnet/minecraft/client/gui/screen/Screen;)V", cancellable = true)
    public void openScreen(Screen screen, CallbackInfo callback) {
        OpenScreen event = new OpenScreen(screen);
        Manager.eventSystem.post(event);
        if (event.isCancelled()) callback.cancel();
    }
}
