package com.bonynew52.bonysmod.events;

import com.bonynew52.bonysmod.entity.ChampionZombie;
import com.bonynew52.bonysmod.registry.ModEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobEvents {
    private static final float THREE_STARS_CHAMPION_CHANCE = 0.002f;  // 0.2%
    private static final float TWO_STARS_CHAMPION_CHANCE = 0.008f;    // 0.6% (acumulado)
    private static final float ONE_STAR_CHAMPION_CHANCE = 0.026f;     // 1.8% (acumulado)
    
    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            Entity entity = event.getEntity();
            
            if (entity instanceof Zombie && !(entity instanceof ChampionZombie)) {
                float random = event.getLevel().getRandom().nextFloat();
                
                if (random < THREE_STARS_CHAMPION_CHANCE) {
                    Level level = event.getLevel();
                    ChampionZombie champion = new ChampionZombie(
                        ModEntities.CHAMPION_ZOMBIE.get(), 
                        level
                    );
                    
                    champion.setPos(entity.getX(), entity.getY(), entity.getZ());
                    champion.setYRot(entity.getYRot());
                    champion.setXRot(entity.getXRot());
                    
                    event.setCanceled(true);
                    level.addFreshEntity(champion);
                }
                else if (random < TWO_STARS_CHAMPION_CHANCE) {
                    makeNormalChampion((Zombie)entity, 2);
                }
                else if (random < ONE_STAR_CHAMPION_CHANCE) {
                    makeNormalChampion((Zombie)entity, 1);
                }
            }
        }
    }

    private static void makeNormalChampion(Zombie zombie, int stars) {
        switch (stars) {
            case 2:
                zombie.addEffect(new MobEffectInstance(MobEffects.SPEED, Integer.MAX_VALUE, 1));
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1));
                zombie.setCustomName(Component.literal("★★ Champion ★★").withStyle(ChatFormatting.AQUA));
                break;
            case 1:
                zombie.addEffect(new MobEffectInstance(MobEffects.SPEED, Integer.MAX_VALUE, 0));
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