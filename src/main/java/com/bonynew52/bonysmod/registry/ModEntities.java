package com.bonynew52.bonysmod.registry;

import com.bonynew52.bonysmod.BonysMod;
import com.bonynew52.bonysmod.entity.ChampionZombie;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BonysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BonysMod.MOD_ID);

    public static final RegistryObject<EntityType<ChampionZombie>> CHAMPION_ZOMBIE = 
        ENTITIES.register("champion_zombie", 
            () -> EntityType.Builder.of(ChampionZombie::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F)
                .build("champion_zombie"));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CHAMPION_ZOMBIE.get(), ChampionZombie.createAttributes().build());
    }
}
