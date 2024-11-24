package com.bonynew52.bonysmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import com.bonynew52.bonysmod.registry.ModEntities;
import com.bonynew52.bonysmod.events.MobEvents;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import com.bonynew52.bonysmod.client.renderer.ChampionZombieRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod(BonysMod.MOD_ID)
public class BonysMod {
    public static final String MOD_ID = "bonysmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BonysMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(modEventBus);
        
        // Registra este mod para eventos
        modEventBus.addListener(this::registerRenderers);
        MinecraftForge.EVENT_BUS.register(MobEvents.class);
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CHAMPION_ZOMBIE.get(), ChampionZombieRenderer::new);
    }
}
