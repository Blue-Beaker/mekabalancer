package io.bluebeaker.mekabalancer.mixin;

import io.bluebeaker.mekabalancer.MekaBalancerConfig;
import mekanism.generators.common.tile.TileEntityGasGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityGasGenerator.class,remap = false)
public abstract class MixinTileGasGenerator {
    @Inject(method = "getToUse",at = @At("RETURN"),cancellable = true)
    public void addRateCap(CallbackInfoReturnable<Integer> cir){
        if(MekaBalancerConfig.generation.gasGeneratorRateCap>0) {
            cir.setReturnValue(Math.min(cir.getReturnValue(),MekaBalancerConfig.generation.gasGeneratorRateCap));
        }
    }
}
