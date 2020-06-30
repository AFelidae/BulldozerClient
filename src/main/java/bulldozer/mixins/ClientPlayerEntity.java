package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.events.Tick;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.network.ClientPlayerEntity.class)
public class ClientPlayerEntity {

    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo callback) {

        Tick event = new Tick();
        Manager.eventSystem.post(event);
        if (event.isCancelled()) callback.cancel();
    }

    /*
    @Inject(at = @At("HEAD"), method = "sendMovementPackets()V", cancellable = true)
    public void sendMovementPackets(CallbackInfo info) {
        EventMovementTick event = new EventMovementTick();
        ToucanHack.eventBus.post(new EventMovementTick());
        if (event.isCancelled()) info.cancel();
    }*/

}

