package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.events.Render3D;
import bulldozer.utils.Options;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.GameRenderer.class)
public class GameRenderer {

    @Inject(at = @At("HEAD"), method = "renderHand(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/Camera;F)V", cancellable = true)
    private void renderHand(MatrixStack matrixStack_1, Camera camera_1, float float_1, CallbackInfo info) {
        Render3D event = new Render3D();
        Manager.eventSystem.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Redirect(
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/options/GameOptions;fov:D",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0),
            method = {"getFov(Lnet/minecraft/client/render/Camera;FZ)D"})
    private double getFov(GameOptions options) {
        return options.fov / Options.zoom;
    }
}

