package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.events.Render3D;
import bulldozer.gui.SettingFloat;
import bulldozer.module.UnfocusedCPU;
import bulldozer.module.Zoom;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.MinecraftClient;


import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.GameRenderer.class)
public class GameRenderer {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRenderHead(float tickDelta, long startTime, boolean tick, CallbackInfo callback) {
        UnfocusedCPU ucpu = (UnfocusedCPU) Manager.getModule(UnfocusedCPU.class);
        if(ucpu != null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (ucpu.getToggled() && !mc.isWindowFocused()) callback.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderHand(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/Camera;F)V", cancellable = true)
    private void renderHand(MatrixStack matrixStack_1, Camera camera_1, float float_1, CallbackInfo callback) {
        Render3D event = new Render3D();
        Manager.eventSystem.post(event);
        if (event.isCancelled()) callback.cancel();
    }

    @Redirect(
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/options/GameOptions;fov:D",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0),
            method = {"getFov(Lnet/minecraft/client/render/Camera;FZ)D"})
    private double getFov(GameOptions options) {
        Zoom zoom = (Zoom) Manager.getModule(Zoom.class);
        if (zoom == null || !zoom.getToggled()) return options.fov;
        return options.fov / ((SettingFloat) zoom.getSettings()[0]).getValue();
    }
}

