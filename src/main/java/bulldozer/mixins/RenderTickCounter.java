package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.gui.SettingFloat;
import bulldozer.module.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.render.RenderTickCounter.class)
public class RenderTickCounter {

    @Shadow
    public float lastFrameDuration;

    @Shadow
    public float tickDelta;

    @Shadow
    private long prevTimeMillis;

    @Shadow
    private float tickTime;

    @Inject(at = @At("HEAD"), method = "beginRenderTick", cancellable = true)
    public void beginRenderTick(long time, CallbackInfoReturnable<Integer> callback) {
        Timer timer = (Timer) Manager.getModule(Timer.class);
        if (timer != null && timer.getToggled()) {
            this.lastFrameDuration = (float) (((time - this.prevTimeMillis) / this.tickTime)
                    * ((SettingFloat) timer.getSettings()[0]).getValue());
            this.prevTimeMillis = time;
            this.tickDelta += this.lastFrameDuration;
            int i = (int) this.tickDelta;
            this.tickDelta -= i;

            callback.setReturnValue(i);
            callback.cancel();
        }
    }

}