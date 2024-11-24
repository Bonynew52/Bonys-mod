package com.bonynew52.bonysmod.client.renderer;

import com.bonynew52.bonysmod.entity.ChampionZombie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ChampionZombieRenderer extends ZombieRenderer {
    public ChampionZombieRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie zombie) {
        return new ResourceLocation("textures/entity/zombie/zombie.png");
    }
} 