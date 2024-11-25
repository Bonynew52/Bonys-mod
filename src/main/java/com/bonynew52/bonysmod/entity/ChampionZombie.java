package com.bonynew52.bonysmod.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChampionZombie extends Zombie {
    private int stars = 3; // Por defecto 3 estrellas

    public ChampionZombie(EntityType<? extends Zombie> type, Level level) {
        this(type, level, 3); // Por defecto 3 estrellas
    }

    public ChampionZombie(EntityType<? extends Zombie> type, Level level, int stars) {
        super(type, level);
        this.stars = stars;
        applyChampionEffects();
    }

    private void applyChampionEffects() {
        switch (stars) {
            case 3:
                // Campeón 3 estrellas - Efectos muy potentes
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 2));      // Fuerza III
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1)); // Resistencia II
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 14));   // Velocidad XV
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Integer.MAX_VALUE, 1));      // Regeneración II
                this.setCustomName(Component.literal("★★★ Champion ★★★").withStyle(ChatFormatting.GOLD));
                break;
            
            case 2:
                // Campeón 2 estrellas - Efectos moderados
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 4));    // Velocidad V
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1));      // Fuerza II
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0)); // Resistencia I
                this.setCustomName(Component.literal("★★ Champion ★★").withStyle(ChatFormatting.AQUA));
                break;
            
            case 1:
                // Campeón 1 estrella - Efectos básicos
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 1));    // Velocidad II
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 0));      // Fuerza I
                this.setCustomName(Component.literal("★ Champion ★").withStyle(ChatFormatting.GREEN));
                break;
        }
        this.setCustomNameVisible(true);
    }

    public int getStars() {
        return this.stars;
    }

    @Override
    public boolean onClimbable() {
        // Solo los zombies de 3 estrellas pueden escalar paredes
        return this.stars == 3 ? this.horizontalCollision : super.onClimbable();
    }

    @Override
    public float getScale() {
        return this.stars == 3 ? 1.2F : 1.0F;
    }

    // Sobreescribir las dimensiones
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        EntityDimensions baseDimensions = super.getDimensions(pose);
        return this.stars == 3 ? 
            baseDimensions.scale(1.2F) : 
            baseDimensions;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)          // Más vida que un zombie normal
                .add(Attributes.MOVEMENT_SPEED, 0.23D)      // Velocidad base
                .add(Attributes.ATTACK_DAMAGE, 4.0D)        // Más daño base
                .add(Attributes.ARMOR, 2.0D)                // Armadura base
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D); // Sin refuerzos
    }
}