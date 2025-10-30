package io.bluebeaker.mekabalancer.mixin;

import mekanism.common.Upgrade;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.tile.TileEntityElectrolyticSeparator;
import mekanism.common.tile.prefab.TileEntityMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.bluebeaker.mekabalancer.MekaBalancerConfig.machines;
import static mekanism.common.block.states.BlockStateMachine.MachineType.ELECTROLYTIC_SEPARATOR;

@Mixin(value = TileEntityElectrolyticSeparator.class,remap = false)
public abstract class MixinTileElectrolyzer extends TileEntityMachine {
    public MixinTileElectrolyzer(String sound, BlockStateMachine.MachineType type, int upgradeSlot) {
        super(sound, type, upgradeSlot);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void recalculateEfficiency(CallbackInfo ci){
        energyPerTick = ELECTROLYTIC_SEPARATOR.getUsage() * Math.pow(machines.electrolyzerSpeedCost, this.getComponent().getUpgrades(Upgrade.SPEED)) / machines.electrolyzerEfficiency;
    }

    @Inject(method = "recalculateUpgradables",at = @At(value = "RETURN"))
    public void recalculateEfficiency(Upgrade upgrade, CallbackInfo ci){
        energyPerTick = ELECTROLYTIC_SEPARATOR.getUsage() * Math.pow(machines.electrolyzerSpeedCost, this.getComponent().getUpgrades(Upgrade.SPEED)) / machines.electrolyzerEfficiency;
    }
}
