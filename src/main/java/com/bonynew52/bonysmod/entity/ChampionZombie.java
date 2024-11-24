package com.bonynew52.bonysmod.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class ChampionZombie extends Zombie {
    public ChampionZombie(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        
        // Efectos base de campeón 3 estrellas
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 2));
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
        
        // Nombre personalizado
        this.setCustomName(Component.literal("★★★ Champion ★★★").withStyle(ChatFormatting.GOLD));
        this.setCustomNameVisible(true);
    }

    @Override
    public boolean onClimbable() {
        return this.horizontalCollision;
    }
}