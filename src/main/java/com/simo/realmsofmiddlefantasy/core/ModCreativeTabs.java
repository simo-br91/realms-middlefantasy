package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealmsOfMiddleFantasy.MODID);

    public static final RegistryObject<CreativeModeTab> REALMS_TAB =
            CREATIVE_MODE_TABS.register("realms_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.realms_middlefantasy.realms_tab"))
                            // Icône : lingot de fer noir
                            .icon(() -> new ItemStack(ModItems.BLACK_IRON_INGOT.get()))
                            .displayItems((params, output) -> {
                                // Ressources
                                output.accept(ModItems.RAW_BLACK_IRON.get());
                                output.accept(ModItems.BLACK_IRON_INGOT.get());

                                // Blocs nains
                                output.accept(ModBlocks.BLACK_IRON_BLOCK.get());
                                output.accept(ModBlocks.DWARVEN_STONE.get());

                                // Equipement
                                output.accept(ModItems.BLACK_IRON_SWORD.get());
                                output.accept(ModItems.BLACK_IRON_CHESTPLATE.get());

                                // Nourriture hobbit
                                output.accept(ModItems.HOBBIT_BREAD.get());

                                // Mobs (tests)
                                output.accept(ModItems.ORC_SPAWN_EGG.get());
                                output.accept(ModItems.DWARF_SPAWN_EGG.get());
                            })
                            .build()
            );
}
