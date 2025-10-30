package io.bluebeaker.mekabalancer.mixin;

import mekanism.common.Upgrade;
import mekanism.common.base.IUpgradeTile;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.MekanismUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.bluebeaker.mekabalancer.MekaBalancerConfig.upgrades;
import static mekanism.common.util.MekanismUtils.fractionUpgrades;

@Mixin(value = MekanismUtils.class,remap = false)
public abstract class MixinMekanismUtils {

    @Inject(method = "getMaxEnergy(Lmekanism/common/base/IUpgradeTile;D)D",at = @At("HEAD"),cancellable = true)
    private static void modifyCapacityEffect(IUpgradeTile mgmt, double def, CallbackInfoReturnable<Double> cir){
        if(!upgrades.enable) return;
        cir.setReturnValue(def * Math.pow(MekanismConfig.current().general.maxUpgradeMultiplier.val(), upgrades.energyUpgradeCapacityEffect * fractionUpgrades(mgmt, Upgrade.ENERGY)));
    }


    @Inject(method = "getTicks",at = @At("HEAD"),cancellable = true)
    private static void modifySpeedUpgradeEffect(IUpgradeTile mgmt, int def, CallbackInfoReturnable<Integer> cir){
        if(!upgrades.enable) return;
        cir.setReturnValue((int) (def * Math.pow(MekanismConfig.current().general.maxUpgradeMultiplier.val(), - upgrades.speedUpgradeEffect * fractionUpgrades(mgmt, Upgrade.SPEED))));
    }

    @Inject(method = "getEnergyPerTick",at = @At("HEAD"),cancellable = true)
    private static void addEfficiencyLoss(IUpgradeTile mgmt, double def, CallbackInfoReturnable<Double> cir){
        if(!upgrades.enable) return;
        cir.setReturnValue(def * Math.pow(MekanismConfig.current().general.maxUpgradeMultiplier.val(),
        upgrades.speedUpgradePowerCost * fractionUpgrades(mgmt, Upgrade.SPEED)
        - upgrades.energyUpgradeCostEffect * fractionUpgrades(mgmt, Upgrade.ENERGY)));
    }
}
