package com.simo.realmsofmiddlefantasy.core;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * Food definitions for the mod.
 * Defines the properties of food items like Hobbit Bread.
 */
public class ModFoods {

    // Hobbit Bread: Provides a good amount of hunger and regenerates health for a short duration.
    public static final FoodProperties HOBBIT_BREAD = new FoodProperties.Builder()
            .nutrition(7)                 // Similar to a good meal
            .saturationMod(0.8f)
            .effect(
                    () -> new MobEffectInstance(MobEffects.REGENERATION, 3 * 20, 0), // 3 seconds of regeneration I
                    1.0f // 100% chance
            )
            .build();
}
