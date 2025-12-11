package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.simo.realmsofmiddlefantasy.entity.ModEntities;
import net.minecraftforge.common.ForgeSpawnEggItem;

/**
 * Base items for the mod (Palier 1 : fer noir + nourriture hobbit).
 */
public class ModItems {

        public static final DeferredRegister<Item> ITEMS =
                DeferredRegister.create(ForgeRegistries.ITEMS, RealmsOfMiddleFantasy.MODID);

        // ─────────────────────────────────────────────────────────
        // Ressources naines : fer noir brut + lingot
        // ─────────────────────────────────────────────────────────

        public static final RegistryObject<Item> RAW_BLACK_IRON = ITEMS.register("raw_black_iron",
                () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> BLACK_IRON_INGOT = ITEMS.register("black_iron_ingot",
                () -> new Item(new Item.Properties()));

        // ─────────────────────────────────────────────────────────
        // Equipement nain : épée & plastron
        // ─────────────────────────────────────────────────────────
        //
        // Remarque d’équilibrage :
        // - On utilise Tiers.IRON et ArmorMaterials.IRON pour ce palier,
        //   histoire de rester simple. On créera un vrai tier/armor material
        //   custom pour le fer noir plus tard (Palier 7).
        // - On donne un peu plus de dégâts de base à l’épée pour la placer
        //   légèrement au-dessus du fer vanilla.

        public static final RegistryObject<Item> BLACK_IRON_SWORD = ITEMS.register("black_iron_sword",
                () -> new SwordItem(
                        Tiers.IRON,
                        4,          // dégâts bonus (épée de fer vanilla = 3)
                        -2.4f,      // vitesse standard d’épée
                        new Item.Properties()
                ));

        public static final RegistryObject<Item> BLACK_IRON_CHESTPLATE = ITEMS.register("black_iron_chestplate",
                () -> new ArmorItem(
                        ArmorMaterials.IRON,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties()
                ));

        // ─────────────────────────────────────────────────────────
        // Nourriture hobbit
        // ─────────────────────────────────────────────────────────

        public static final RegistryObject<Item> HOBBIT_BREAD = ITEMS.register("hobbit_bread",
                () -> new Item(new Item.Properties().food(ModFoods.HOBBIT_BREAD)));
        
        // Ajoute ces lignes dans ModItems.java, dans la section spawn eggs :

        // ─────────────────────────────────────────────────────────
        // Spawn eggs pour tous les mobs
        // ─────────────────────────────────────────────────────────

        // Orcs
        public static final RegistryObject<Item> ORC_SPAWN_EGG = ITEMS.register("orc_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.ORC, 0x2B2B2B, 0x4A7A1C,
                        new Item.Properties()));

        public static final RegistryObject<Item> ORC_SCOUT_SPAWN_EGG = ITEMS.register("orc_scout_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.ORC_SCOUT, 0x1a1a1a, 0x8b4513,
                        new Item.Properties()));

        public static final RegistryObject<Item> ORC_WARRIOR_SPAWN_EGG = ITEMS.register("orc_warrior_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.ORC_WARRIOR, 0x3a3a3a, 0x654321,
                        new Item.Properties()));

        public static final RegistryObject<Item> ORC_CAPTAIN_SPAWN_EGG = ITEMS.register("orc_captain_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.ORC_CAPTAIN, 0x4a4a4a, 0xffd700,
                        new Item.Properties()));

        // Uruk-hai
        public static final RegistryObject<Item> URUK_HAI_SPAWN_EGG = ITEMS.register("uruk_hai_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.URUK_HAI, 0x1f1f1f, 0x8b0000,
                        new Item.Properties()));

        // Goblins
        public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = ITEMS.register("goblin_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.GOBLIN, 0x556b2f, 0x8fbc8f,
                        new Item.Properties()));

        // Wargs
        public static final RegistryObject<Item> WARG_SPAWN_EGG = ITEMS.register("warg_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.WARG, 0x2f2f2f, 0x696969,
                        new Item.Properties()));

        // Trolls (DEUX VARIANTES DISTINCTES)
        public static final RegistryObject<Item> HILL_TROLL_SPAWN_EGG = ITEMS.register("hill_troll_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.HILL_TROLL, 0x5a7350, 0x3e5437,
                        new Item.Properties()));

        public static final RegistryObject<Item> CAVE_TROLL_SPAWN_EGG = ITEMS.register("cave_troll_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.CAVE_TROLL, 0x3d3d3d, 0x1f1f1f,
                        new Item.Properties()));

        // Araignées d'ombre
        public static final RegistryObject<Item> SHADOW_SPIDER_SPAWN_EGG = ITEMS.register("shadow_spider_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.SHADOW_SPIDER, 0x0f0f0f, 0x4b0082,
                        new Item.Properties()));

        // Wights
        public static final RegistryObject<Item> WIGHT_SPAWN_EGG = ITEMS.register("wight_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.WIGHT, 0x708090, 0x00ced1,
                        new Item.Properties()));

        // Peuples Libres
        public static final RegistryObject<Item> DWARF_SPAWN_EGG = ITEMS.register("dwarf_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.DWARF, 0x695a4a, 0xc1a37b,
                        new Item.Properties()));
        
        // Elves
        public static final RegistryObject<Item> HIGH_ELF_WARRIOR_SPAWN_EGG = ITEMS.register("high_elf_warrior_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.HIGH_ELF_WARRIOR, 0xD4AF37, 0x4169E1,
                        new Item.Properties()));

        public static final RegistryObject<Item> SILVAN_ELF_SCOUT_SPAWN_EGG = ITEMS.register("silvan_elf_scout_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.SILVAN_ELF_SCOUT, 0x228B22, 0x8FBC8F,
                        new Item.Properties()));

        public static final RegistryObject<Item> GALADHRIM_ARCHER_SPAWN_EGG = ITEMS.register("galadhrim_archer_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntities.GALADHRIM_ARCHER, 0xFFD700, 0xC0C0C0,
                        new Item.Properties()));
}
