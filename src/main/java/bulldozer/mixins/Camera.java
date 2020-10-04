package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.gui.SettingDouble;
import bulldozer.module.CameraClip;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.render.Camera.class)
public abstract class Camera {
    @Shadow
    private Vec3d pos;

    @Shadow private boolean thirdPerson;

    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Inject(method = "clipToSpace", at = @At("HEAD"), cancellable = true)
    private void onClipToSpace(double distance, CallbackInfoReturnable<Double> callback) {
        CameraClip cameraClip = (CameraClip) Manager.getModule(CameraClip.class);
        if (cameraClip.getToggled()) {
            callback.setReturnValue(((SettingDouble)cameraClip.getSettings()[0]).getValue());
        }
    }
}