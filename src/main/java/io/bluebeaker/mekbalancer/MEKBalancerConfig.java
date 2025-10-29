package io.bluebeaker.mekbalancer;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MEKBalancer.MODID,type = Type.INSTANCE,category = "general")
public class MEKBalancerConfig {
    public static Upgrades upgrades = new Upgrades();
    public static class Upgrades {
        @Comment("Enable tweaks to upgrades")
        public boolean enable = true;
        @Comment("Effect multiplier for each speed upgrade")
        public float speedUpgradeEffect = 1.0f;
        @Comment("Power cost multiplier for each speed upgrade")
        public float speedUpgradePowerCost = 2.0f;
        @Comment("Energy cost reducing multiplier for each energy upgrade")
        public float energyUpgradeCostEffect = 1.0f;
        @Comment("Energy capacity multiplier for each energy upgrade")
        public float energyUpgradeCapacityEffect = 1.0f;
    }
    public static Machines machines = new Machines();
    public static class Machines {
        @Comment("Efficiency for electrolyzer. The power cost for electrolyzer is divided by this value.")
        public float electrolyzerEfficiency = 1.0f;
        @Comment("Extra cost multiplier for each speed upgrade for electrolyzer")
        public float electrolyzerSpeedCost = 1.0f;
    }
    public static Generation generation = new Generation();
    public static class Generation {

    }
}