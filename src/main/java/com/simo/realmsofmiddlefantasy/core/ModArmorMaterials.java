package com.simo.realmsofmiddlefantasy.core;

import com.google.common.base.Suppliers; // Required for 1.20.1 memoization
import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * Armor materials for the mod (Mithril, Galvorn, Black Iron, etc.).
 */
public enum ModArmorMaterials implements ArmorMaterial {

    // Galvorn Armor Material
    GALVORN(
        "galvorn",
        25, // durability multiplier
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 7);
            map.put(ArmorItem.Type.HELMET, 3);
        }),
        18, // enchantability
        SoundEvents.ARMOR_EQUIP_LEATHER, // quiet equip sound
        1.0F, // toughness
        0.0F, // knockback resistance
        () -> Ingredient.of(ModItems.GALVORN_INGOT.get())
    ),

    // Mithril Armor Material
    MITHRIL(
        "mithril",
        33, // durability multiplier (diamond = 33)
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        }),
        20, // enchantability
        SoundEvents.ARMOR_EQUIP_DIAMOND,
        2.0F, // toughness
        0.0F, // knockback resistance
        () -> Ingredient.of(ModItems.MITHRIL_INGOT.get())
    ),

    // Black Iron Armor Material (Added to fix error in ModItems)
    BLACK_IRON(
        "black_iron",
        30, // durability multiplier (Between Iron and Diamond)
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 5);
            map.put(ArmorItem.Type.CHESTPLATE, 7);
            map.put(ArmorItem.Type.HELMET, 3);
        }),
        12, // enchantability (Lower than Mithril)
        SoundEvents.ARMOR_EQUIP_IRON,
        1.0F, // toughness
        0.0F, // knockback resistance
        () -> Ingredient.of(ModItems.BLACK_IRON_INGOT.get())
    );

    // ─────────────────────────────────────────────────────────

    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(
            new EnumMap<>(ArmorItem.Type.class),
            map -> {
                map.put(ArmorItem.Type.BOOTS, 13);
                map.put(ArmorItem.Type.LEGGINGS, 15);
                map.put(ArmorItem.Type.CHESTPLATE, 16);
                map.put(ArmorItem.Type.HELMET, 11);
            }
    );

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protection;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient; // Changed from LazyLoadedValue to Supplier

    ModArmorMaterials(String name,
                      int durabilityMultiplier,
                      EnumMap<ArmorItem.Type, Integer> protection,
                      int enchantability,
                      SoundEvent equipSound,
                      float toughness,
                      float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protection = protection;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        // 1.20.1 Fix: Use Suppliers.memoize instead of LazyLoadedValue
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protection.get(type);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return RealmsOfMiddleFantasy.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}