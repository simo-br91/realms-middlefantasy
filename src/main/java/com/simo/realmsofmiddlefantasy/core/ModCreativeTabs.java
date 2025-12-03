package com.simo.realmsofmiddlefantasy.core;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Onglet créatif personnalisé pour le mod.
 * Palier 0 : Tab vide avec icône placeholder (sera remplacé par un item emblématique au Palier 1).
 */
public class ModCreativeTabs {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealmsOfMiddleFantasy.MODID);
    
    public static final RegistryObject<CreativeModeTab> REALMS_TAB = CREATIVE_MODE_TABS.register("realms_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.realms_middlefantasy.realms_tab"))
            .icon(() -> new ItemStack(Items.IRON_SWORD)) // Placeholder - sera remplacé au Palier 1
            .displayItems((parameters, output) -> {
                // Les items seront ajoutés ici automatiquement via ModItems aux paliers suivants
            })
            .build());
}
