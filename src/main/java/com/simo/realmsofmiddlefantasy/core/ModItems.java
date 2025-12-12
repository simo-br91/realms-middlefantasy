package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.ForgeSpawnEggItem;

/**
 * This class registers all the necessary items used throughout the mod.
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, RealmsOfMiddleFantasy.MODID);

    // ─────────────────────────────────────────────────────────
    // Raw Materials and Ingots
    // ─────────────────────────────────────────────────────────

    // Black Iron Items
    public static final RegistryObject<Item> RAW_BLACK_IRON = ITEMS.register("raw_black_iron", 
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_IRON_INGOT = ITEMS.register("black_iron_ingot", 
        () -> new Item(new Item.Properties()));

    // Mithril Items
    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", 
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_ORE = ITEMS.register("mithril_ore", 
        () -> new BlockItem(ModBlocks.MITHRIL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_BLOCK = ITEMS.register("mithril_block", 
        () -> new BlockItem(ModBlocks.MITHRIL_BLOCK.get(), new Item.Properties()));

    // Galvorn Items
    public static final RegistryObject<Item> GALVORN_INGOT = ITEMS.register("galvorn_ingot", 
        () -> new Item(new Item.Properties()));

    // ─────────────────────────────────────────────────────────
    // Tools and Weapons
    // ─────────────────────────────────────────────────────────

    // Black Iron Tools & Armor (Added to match Creative Tab)
    public static final RegistryObject<Item> BLACK_IRON_SWORD = ITEMS.register("black_iron_sword", 
        () -> new SwordItem(ModTiers.BLACK_IRON, 3, -2.4F, new Item.Properties()));
    
    public static final RegistryObject<Item> BLACK_IRON_CHESTPLATE = ITEMS.register("black_iron_chestplate", 
        () -> new ArmorItem(ModArmorMaterials.BLACK_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    // Mithril Tools and Armor
    public static final RegistryObject<Item> MITHRIL_SWORD = ITEMS.register("mithril_sword", 
        () -> new SwordItem(ModTiers.MITHRIL, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe", 
        () -> new PickaxeItem(ModTiers.MITHRIL, 1, -2.8F, new Item.Properties()));

    // Mithril Armor
    public static final RegistryObject<Item> MITHRIL_HELMET = ITEMS.register("mithril_helmet", 
        () -> new ArmorItem(ModArmorMaterials.MITHRIL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate", 
        () -> new ArmorItem(ModArmorMaterials.MITHRIL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_LEGGINGS = ITEMS.register("mithril_leggings", 
        () -> new ArmorItem(ModArmorMaterials.MITHRIL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_BOOTS = ITEMS.register("mithril_boots", 
        () -> new ArmorItem(ModArmorMaterials.MITHRIL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Galvorn Armor
    public static final RegistryObject<Item> GALVORN_HELMET = ITEMS.register("galvorn_helmet", 
        () -> new ArmorItem(ModArmorMaterials.GALVORN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> GALVORN_CHESTPLATE = ITEMS.register("galvorn_chestplate", 
        () -> new ArmorItem(ModArmorMaterials.GALVORN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> GALVORN_LEGGINGS = ITEMS.register("galvorn_leggings", 
        () -> new ArmorItem(ModArmorMaterials.GALVORN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> GALVORN_BOOTS = ITEMS.register("galvorn_boots", 
        () -> new ArmorItem(ModArmorMaterials.GALVORN, ArmorItem.Type.BOOTS, new Item.Properties()));

    // ─────────────────────────────────────────────────────────
    // Hobbit Food
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<Item> HOBBIT_BREAD = ITEMS.register("hobbit_bread", 
        () -> new Item(new Item.Properties().food(ModFoods.HOBBIT_BREAD)));

    // ─────────────────────────────────────────────────────────
    // Spawn Eggs
    // ─────────────────────────────────────────────────────────

    // Orcs and Variants
    public static final RegistryObject<Item> ORC_SPAWN_EGG = ITEMS.register("orc_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.ORC, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> ORC_SCOUT_SPAWN_EGG = ITEMS.register("orc_scout_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.ORC_SCOUT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> ORC_WARRIOR_SPAWN_EGG = ITEMS.register("orc_warrior_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.ORC_WARRIOR, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> ORC_CAPTAIN_SPAWN_EGG = ITEMS.register("orc_captain_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.ORC_CAPTAIN, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Uruk-hai
    public static final RegistryObject<Item> URUK_HAI_SPAWN_EGG = ITEMS.register("uruk_hai_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.URUK_HAI, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Goblins
    public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = ITEMS.register("goblin_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.GOBLIN, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Wargs
    public static final RegistryObject<Item> WARG_SPAWN_EGG = ITEMS.register("warg_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.WARG, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Trolls (Variants)
    public static final RegistryObject<Item> HILL_TROLL_SPAWN_EGG = ITEMS.register("hill_troll_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.HILL_TROLL, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> CAVE_TROLL_SPAWN_EGG = ITEMS.register("cave_troll_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.CAVE_TROLL, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Shadow Spiders
    public static final RegistryObject<Item> SHADOW_SPIDER_SPAWN_EGG = ITEMS.register("shadow_spider_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.SHADOW_SPIDER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Wights
    public static final RegistryObject<Item> WIGHT_SPAWN_EGG = ITEMS.register("wight_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.WIGHT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Free Peoples (Elves, Dwarves, Men)
    public static final RegistryObject<Item> DWARF_SPAWN_EGG = ITEMS.register("dwarf_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.DWARF, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> HIGH_ELF_WARRIOR_SPAWN_EGG = ITEMS.register("high_elf_warrior_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.HIGH_ELF_WARRIOR, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> SILVAN_ELF_SCOUT_SPAWN_EGG = ITEMS.register("silvan_elf_scout_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.SILVAN_ELF_SCOUT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> GALADHRIM_ARCHER_SPAWN_EGG = ITEMS.register("galadhrim_archer_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.GALADHRIM_ARCHER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> DUNEDAIN_RANGER_SPAWN_EGG = ITEMS.register("dunedain_ranger_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.DUNEDAIN_RANGER, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> MAN_OF_NORTH_SPAWN_EGG = ITEMS.register("man_of_north_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.MAN_OF_NORTH, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> CITADEL_GUARD_SPAWN_EGG = ITEMS.register("citadel_guard_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.CITADEL_GUARD, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> SOLDIER_OF_GONDOR_SPAWN_EGG = ITEMS.register("soldier_of_gondor_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.SOLDIER_OF_GONDOR, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> HOBBIT_SPAWN_EGG = ITEMS.register("hobbit_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.HOBBIT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    // Added Spawn Eggs (Were in Creative Tab but missing here)
    public static final RegistryObject<Item> IRON_HILLS_WARRIOR_SPAWN_EGG = ITEMS.register("iron_hills_warrior_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.IRON_HILLS_WARRIOR, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> ENT_SHEPHERD_SPAWN_EGG = ITEMS.register("ent_shepherd_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.ENT_SHEPHERD, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> HUORN_SPAWN_EGG = ITEMS.register("huorn_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.HUORN, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> GREAT_EAGLE_SPAWN_EGG = ITEMS.register("great_eagle_spawn_egg", 
        () -> new ForgeSpawnEggItem(ModEntities.GREAT_EAGLE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
}