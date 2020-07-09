package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.events.ClientMove;
import bulldozer.events.Tick;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
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

    @Inject(at = @At("HEAD"), method = "move", cancellable = true)
    public void move(MovementType movementType_1, Vec3d vec3d_1, CallbackInfo callback) {
        ClientMove event = new ClientMove(vec3d_1);
        Manager.eventSystem.post(event);
        if (event.isCancelled()) callback.cancel();
    }
}

