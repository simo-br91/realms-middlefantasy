package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Custom Creative Mode Tabs for the mod.
 * Registers and organizes all the items, blocks, and spawn eggs for display in the Creative Mode inventory.
 */
public class ModCreativeTabs {

    // Deferred register for Creative Mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealmsOfMiddleFantasy.MODID);

    /**
     * The main "Realms" creative tab for the mod.
     * Displays a variety of items such as ores, blocks, armor, weapons, and spawn eggs.
     */
    public static final RegistryObject<CreativeModeTab> REALMS_TAB =
            CREATIVE_MODE_TABS.register("realms_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.realms_middlefantasy.realms_tab")) // Title of the tab
                            // Icon: black iron ingot
                            .icon(() -> new ItemStack(ModItems.BLACK_IRON_INGOT.get()))
                            .displayItems((params, output) -> {
                                // ──────────────────────────────────────
                                // Resources & Ores
                                // ──────────────────────────────────────
                                output.accept(ModItems.RAW_BLACK_IRON.get());
                                output.accept(ModItems.BLACK_IRON_INGOT.get());
                                
                                // Added Mithril
                                output.accept(ModItems.MITHRIL_ORE.get());
                                output.accept(ModItems.MITHRIL_BLOCK.get());
                                output.accept(ModItems.MITHRIL_INGOT.get());

                                // Added Galvorn
                                output.accept(ModItems.GALVORN_INGOT.get());

                                // Dwarven blocks
                                output.accept(ModBlocks.BLACK_IRON_BLOCK.get());
                                output.accept(ModBlocks.DWARVEN_STONE.get());

                                // ──────────────────────────────────────
                                // Equipment (Tools, Weapons, Armor)
                                // ──────────────────────────────────────
                                
                                // Black Iron
                                output.accept(ModItems.BLACK_IRON_SWORD.get());
                                output.accept(ModItems.BLACK_IRON_CHESTPLATE.get());

                                // Added Mithril Equipment
                                output.accept(ModItems.MITHRIL_SWORD.get());
                                output.accept(ModItems.MITHRIL_PICKAXE.get());
                                output.accept(ModItems.MITHRIL_HELMET.get());
                                output.accept(ModItems.MITHRIL_CHESTPLATE.get());
                                output.accept(ModItems.MITHRIL_LEGGINGS.get());
                                output.accept(ModItems.MITHRIL_BOOTS.get());

                                // Added Galvorn Equipment
                                output.accept(ModItems.GALVORN_HELMET.get());
                                output.accept(ModItems.GALVORN_CHESTPLATE.get());
                                output.accept(ModItems.GALVORN_LEGGINGS.get());
                                output.accept(ModItems.GALVORN_BOOTS.get());

                                // ──────────────────────────────────────
                                // Food
                                // ──────────────────────────────────────
                                output.accept(ModItems.HOBBIT_BREAD.get());

                                // ──────────────────────────────────────
                                // Spawn Eggs
                                // ──────────────────────────────────────
                                output.accept(ModItems.ORC_SPAWN_EGG.get());
                                output.accept(ModItems.ORC_SCOUT_SPAWN_EGG.get());
                                output.accept(ModItems.ORC_WARRIOR_SPAWN_EGG.get());
                                output.accept(ModItems.ORC_CAPTAIN_SPAWN_EGG.get());

                                output.accept(ModItems.URUK_HAI_SPAWN_EGG.get());
                                output.accept(ModItems.GOBLIN_SPAWN_EGG.get());
                                output.accept(ModItems.WARG_SPAWN_EGG.get());
                                
                                output.accept(ModItems.HILL_TROLL_SPAWN_EGG.get());
                                output.accept(ModItems.CAVE_TROLL_SPAWN_EGG.get());
                                
                                output.accept(ModItems.SHADOW_SPIDER_SPAWN_EGG.get());
                                output.accept(ModItems.WIGHT_SPAWN_EGG.get());
                                
                                output.accept(ModItems.DWARF_SPAWN_EGG.get());
                                output.accept(ModItems.HIGH_ELF_WARRIOR_SPAWN_EGG.get());
                                output.accept(ModItems.SILVAN_ELF_SCOUT_SPAWN_EGG.get());
                                output.accept(ModItems.GALADHRIM_ARCHER_SPAWN_EGG.get());
                                output.accept(ModItems.DUNEDAIN_RANGER_SPAWN_EGG.get());
                                output.accept(ModItems.MAN_OF_NORTH_SPAWN_EGG.get());
                                output.accept(ModItems.CITADEL_GUARD_SPAWN_EGG.get());
                                output.accept(ModItems.SOLDIER_OF_GONDOR_SPAWN_EGG.get());
                                output.accept(ModItems.HOBBIT_SPAWN_EGG.get());
                                
                                // These now exist in ModItems
                                output.accept(ModItems.IRON_HILLS_WARRIOR_SPAWN_EGG.get());
                                output.accept(ModItems.ENT_SHEPHERD_SPAWN_EGG.get());
                                output.accept(ModItems.HUORN_SPAWN_EGG.get());
                                output.accept(ModItems.GREAT_EAGLE_SPAWN_EGG.get());
                            })
                            .build()
            );
}