package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * Food definitions for the mod.
 */
public class ModFoods {

    // Pain hobbit : nourrit bien, petite regen très courte.
    public static final FoodProperties HOBBIT_BREAD = new FoodProperties.Builder()
            .nutrition(7)                 // proche d’un bon repas
            .saturationMod(0.8f)
            .effect(
                    () -> new MobEffectInstance(MobEffects.REGENERATION, 3 * 20, 0), // 3s de regen I
                    1.0f // 100% de chance
            )
            .build();
}
