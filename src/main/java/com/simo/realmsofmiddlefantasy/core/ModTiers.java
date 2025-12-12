package com.simo.realmsofmiddlefantasy.core;

import com.google.common.base.Suppliers; // Required for 1.20.1 memoization
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

/**
 * Custom Tiers for the mod's tools and weapons.
 * Defines the properties of various material tiers used for tools, weapons, and armor.
 */
public enum ModTiers implements Tier {

    // ─────────────────────────────────────────────────────────
    // TIER DEFINITIONS
    // ─────────────────────────────────────────────────────────

    // MITHRIL: High speed, high enchantability (Elven/Holy feel)
    MITHRIL(
            4,              // Mining level (4 = Netherite)
            2031,           // Uses (Same as Netherite)
            10.0F,          // Mining speed (Faster than Netherite)
            3.0F,           // Attack damage bonus
            18,             // Enchantability (High)
            () -> Ingredient.of(ModItems.MITHRIL_INGOT.get())
    ),

    // BLACK IRON (Galvorn): Very durable, hard hitting (Eöl's metal)
    BLACK_IRON(
            3,              // Mining level (3 = Diamond equivalent)
            1800,           // Uses (Between Diamond and Netherite)
            8.0F,           // Mining speed (Good speed)
            3.5F,           // Attack damage bonus (Slightly higher raw damage)
            12,             // Enchantability (Moderate)
            () -> Ingredient.of(ModItems.BLACK_IRON_INGOT.get())
    );

    // ─────────────────────────────────────────────────────────
    // PROPERTIES
    // ─────────────────────────────────────────────────────────

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient; // Changed from LazyLoadedValue to Supplier for 1.20.1

    ModTiers(int level, int uses, float speed, float damage, int enchantmentValue,
             Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        // Uses Guava's Suppliers.memoize to replace the old LazyLoadedValue
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}