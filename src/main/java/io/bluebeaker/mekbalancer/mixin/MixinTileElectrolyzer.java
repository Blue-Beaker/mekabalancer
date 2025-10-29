package io.bluebeaker.mekbalancer.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluebeaker.mekbalancer.MEKBalancerConfig;
import mekanism.common.Upgrade;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.tile.TileEntityElectrolyticSeparator;
import mekanism.common.tile.prefab.TileEntityMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.bluebeaker.mekbalancer.MEKBalancerConfig.machines;
import static io.bluebeaker.mekbalancer.MEKBalancerConfig.upgrades;

@Mixin(value = TileEntityElectrolyticSeparator.class,remap = false)
public abstract class MixinTileElectrolyzer extends TileEntityMachine {
    public MixinTileElectrolyzer(String sound, BlockStateMachine.MachineType type, int upgradeSlot) {
        super(sound, type, upgradeSlot);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void addEfficiencyLoss(CallbackInfo ci){
        energyPerTick = energyPerTick / machines.electrolyzerEfficiency;
    }

    @Inject(method = "recalculateUpgradables",at = @At(value = "RETURN"))
    public void addEfficiencyLoss(Upgrade upgrade, CallbackInfo ci){
        if(upgrade == Upgrade.SPEED){
            energyPerTick = BlockStateMachine.MachineType.ELECTROLYTIC_SEPARATOR.getUsage() * Math.pow(machines.electrolyzerSpeedCost, upgrade.getStack().getCount()) / machines.electrolyzerEfficiency;
        }
    }
}
