package com.outrightwings.albanes_extra_textures;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import sekelsta.horse_colors.entity.genetics.EquineGenome;
import sekelsta.horse_colors.entity.genetics.HorsePatternCalculator;

import java.lang.reflect.Method;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "albanes_extra_textures";

    public Main()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT,Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
       SplashFactorAccessor.LoadMethod();
    }
}
