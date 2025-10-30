package io.bluebeaker.mekabalancer.mixin;

import io.bluebeaker.mekabalancer.MekaBalancerConfig;
import mekanism.generators.common.FusionReactor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = FusionReactor.class,remap = false)
public abstract class MixinFusionReactor {
    @ModifyConstant(method = "setInjectionRate",constant = @Constant(intValue = 98))
    public int modifyInjectionRateCap(int constant){
        return MekaBalancerConfig.generation.fusionMaxInjection;
    }
}
