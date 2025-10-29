package io.bluebeaker.mekbalancer;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MEKBalancer.MODID,type = Type.INSTANCE,category = "general")
public class MEKBalancerConfig {
    @Comment("Example")
    @LangKey("config.mekbalancer.example.name")
    public static boolean example = true;
}