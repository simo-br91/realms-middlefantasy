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
    
        // ─────────────────────────────────────────────────────────
    // Spawn egg d'Orc (pour tests Palier 2)
    // ─────────────────────────────────────────────────────────

    public static final RegistryObject<Item> ORC_SPAWN_EGG = ITEMS.register("orc_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    ModEntities.ORC,
                    0x2B2B2B, // couleur primaire (gris sombre)
                    0x4A7A1C, // couleur secondaire (vert sale)
                    new Item.Properties()
            ));
    public static final RegistryObject<Item> DWARF_SPAWN_EGG = ITEMS.register("dwarf_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DWARF, 0x695a4a, 0xc1a37b,
                    new Item.Properties()));


}
