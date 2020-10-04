package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.module.EntityControl;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.entity.passive.LlamaEntity.class)
public class LlamaEntity {
    @Inject(at = @At("HEAD"), method = "canBeControlledByRider()Z", cancellable = true)
    public void canBeControlledByRider(CallbackInfoReturnable<Boolean> callback) {
        callback.setReturnValue(Manager.getModule(EntityControl.class).getToggled());
    }
}
