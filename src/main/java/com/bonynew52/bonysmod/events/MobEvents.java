package com.bonynew52.bonysmod.events;

import com.bonynew52.bonysmod.entity.ChampionZombie;
import com.bonynew52.bonysmod.registry.ModEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import static com.bonynew52.bonysmod.BonysMod.LOGGER;

public class MobEvents {
    private static final float THREE_STARS_CHAMPION_CHANCE = 0.20f;  // 20%
    private static final float TWO_STARS_CHAMPION_CHANCE = 0.30f;    // 30%
    private static final float ONE_STAR_CHAMPION_CHANCE = 0.50f;     // 50%
    
    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent event) {
        LOGGER.debug("Entity Spawn Event: {}", event.getEntity().getType());

        if (event.getEntity() instanceof Zombie && !(event.getEntity() instanceof ChampionZombie)) {
            LOGGER.info("Zombie normal detectado");
            float roll = event.getLevel().getRandom().nextFloat();
            LOGGER.info("Roll: {}, Chances: 1★={}, 2★={}, 3★={}", 
                roll, ONE_STAR_CHAMPION_CHANCE, TWO_STARS_CHAMPION_CHANCE, THREE_STARS_CHAMPION_CHANCE);

            if (roll < ONE_STAR_CHAMPION_CHANCE) {
                LOGGER.info("Creando zombie campeón de 1 estrella");
                Level level = event.getLevel();
                ChampionZombie champion = new ChampionZombie(
                    ModEntities.CHAMPION_ZOMBIE.get(), 
                    level
                );
                
                champion.setPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                champion.setYRot(event.getEntity().getYRot());
                champion.setXRot(event.getEntity().getXRot());
                
                event.setCanceled(true);
                level.addFreshEntity(champion);
                LOGGER.info("Zombie campeón creado exitosamente");
            }
            else if (roll < TWO_STARS_CHAMPION_CHANCE) {
                makeNormalChampion((Zombie)event.getEntity(), 2);
            }
            else if (roll < ONE_STAR_CHAMPION_CHANCE) {
                LOGGER.info("¡Intentando crear zombie campeón!");
                makeNormalChampion((Zombie)event.getEntity(), 1);
            }
        }
    }

    private static void makeNormalChampion(Zombie zombie, int stars) {
        switch (stars) {
            case 2:
                zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 1));
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1));
                zombie.setCustomName(Component.literal("★★ Champion ★★").withStyle(ChatFormatting.AQUA));
                break;
            case 1:
                zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 0));
                zombie.setCustomName(Component.literal("★ Champion ★").withStyle(ChatFormatting.GREEN));
                break;
        }
        zombie.setCustomNameVisible(true);
    }

    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            LivingEntity killed = event.getEntity();
            Entity killer = event.getSource().getEntity();
            
            // Si el asesino es un jugador y el mob muerto tiene efectos
            if (killer instanceof Player && !killed.getActiveEffects().isEmpty()) {
                // Aquí añadirías los puntos
            }
        }
    }
} 