package com.bonynew52.bonysmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.bonynew52.bonysmod.registry.ModEntities;

@Mod(BonysMod.MOD_ID)
public class BonysMod {
    public static final String MOD_ID = "bonysmod";

    public BonysMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(modEventBus);
    }
}
